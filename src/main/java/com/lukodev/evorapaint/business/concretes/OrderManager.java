package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.*;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.services.automaticMail.AutomaticMailService;
import com.lukodev.evorapaint.core.utilities.results.*;
import com.lukodev.evorapaint.dataAccess.abstracts.OrderDao;
import com.lukodev.evorapaint.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager implements OrderService{

    private OrderDao orderDao;
    private OrderProductService orderProductService;
    private CustomerService customerService;
    private CompanyService companyService;
    private OrderStatusService orderStatusService;
    private AutomaticMailService automaticMailService;
    private ShipmentService shipmentService;
    private ShoppingCartItemService shoppingCartItemService;
    private ShoppingCartService shoppingCartService;
    private int daysToEstimatedDeliveryDate = 10;
    @Autowired
    public OrderManager(OrderDao orderDao, OrderProductService orderProductService, CustomerService customerService, CompanyService companyService, OrderStatusService orderStatusService, AutomaticMailService automaticMailService, ShipmentService shipmentService, ShoppingCartItemService shoppingCartItemService, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.orderProductService = orderProductService;
        this.customerService = customerService;
        this.companyService = companyService;
        this.orderStatusService = orderStatusService;
        this.automaticMailService = automaticMailService;
        this.shipmentService = shipmentService;
        this.shoppingCartItemService = shoppingCartItemService;
        this.shoppingCartService = shoppingCartService;
    }

    @Transactional
    @CacheEvict(value = "order.getAll",allEntries = true)
    @Override
    public Result add(Order order) {
        DataResult<Boolean> customerHaveCompanyResult = this.checkCustomerHaveCompany(order.getCustomer());
        if(!customerHaveCompanyResult.getData()){
            return customerHaveCompanyResult;
        }
        this.orderDao.save(order);
        sendOrderStatusMail(order);
        return new SuccessResult(Messages.ORDER_ADDED);
    }

    @Transactional
    @CacheEvict(value = "order.getAll",allEntries = true)
    @Override
    public Result update(Order order) {
        DataResult<Boolean> customerHaveCompanyResult = this.checkCustomerHaveCompany(order.getCustomer());
        if(!customerHaveCompanyResult.getData()){
            return customerHaveCompanyResult;
        }
        this.orderDao.save(order);
        sendOrderStatusMail(order);
        return new SuccessResult(Messages.ORDER_UPDATED);
    }

    @CacheEvict(value = "order.getAll",allEntries = true)
    @Override
    public Result delete(Order order) {
        this.orderDao.delete(order);
        return new SuccessResult(Messages.ORDER_DELETED);
    }

    @CacheEvict(value = "order.getAll",allEntries = true)
    @Transactional
    @Override
    public Result shoppingCartToOrderByShoppingCartId(int shoppingCartId,
                                                      ShipmentMethod shipmentMethod, Address address, boolean belongingToCompany) {
        DataResult<List<ShoppingCartItem>> shoppingCartItemsResult = this.shoppingCartItemService.getAllByShoppingCartId(shoppingCartId);
        if(!shoppingCartItemsResult.isSuccess()){
            return new ErrorResult("Sipariş verilirken bi hata oluştu.");
        }
        Customer customer = shoppingCartItemsResult.getData().get(0).getShoppingCart().getCustomer();
        Order order = this.createOrderByCustomer(customer, shipmentMethod, address, belongingToCompany);
        Result toOrderProductResult = this.shoppingCartItemsToOrderProducts(shoppingCartItemsResult.getData(), order);
        if(!toOrderProductResult.isSuccess()){
            return toOrderProductResult;
        }
        this.shoppingCartService.deleteByCustomerId(customer.getId());
        return new SuccessResult("Sipariş oluşturuldu");
    }

    @Transactional
    public DataResult<List<OrderProduct>> shoppingCartItemsToOrderProducts(List<ShoppingCartItem> shoppingCartItems, Order order){
        List<OrderProduct> orderProducts = new ArrayList<>();
        for(ShoppingCartItem shoppingCartItem : shoppingCartItems){
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(shoppingCartItem.getProduct());
            orderProduct.setPackageType(shoppingCartItem.getPackageType());
            orderProduct.setPackageTypeCount(shoppingCartItem.getPackageTypeCount());
            orderProduct.setQuantity(shoppingCartItem.getQuantity());
            orderProducts.add(orderProduct);
        }
        Result addOrderProductResult = this.orderProductService.addAll(orderProducts);
        if(!addOrderProductResult.isSuccess()){
            return new ErrorDataResult<>(addOrderProductResult.getMessage());
        }
        return new SuccessDataResult<>(orderProducts);
    }

    private Order createOrderByCustomer(Customer customer, ShipmentMethod shipmentMethod, Address address, boolean belongingToCompany){
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1);
        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setEstimatedDeliveryDate(LocalDate.now().plusDays(daysToEstimatedDeliveryDate));
        order.setCustomer(customer);
        order.setShipmentMethod(shipmentMethod);
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order.setBelongingToCompany(belongingToCompany);
        this.add(order);
        return order;
    }

    @Cacheable(value = "order.getAll")
    @Override
    public DataResult<List<Order>> getAll() {
        return new SuccessDataResult<>(this.orderDao.findAll());
    }

    @Override
    public DataResult<List<Order>> getAllByCustomerId(int customerId) {
        return new SuccessDataResult<>(this.orderDao.getAllByCustomerId(customerId));
    }

    @Override
    public DataResult<List<Order>> getAllByOrderStatusId(int orderStatusId) {
        return new SuccessDataResult<>(this.orderDao.getAllByOrderStatusId(orderStatusId));
    }

    @Override
    public DataResult<Order> getById(int id) {
        return new SuccessDataResult<>(this.orderDao.findById(id).get());
    }

    public Result sendOrderStatusMail(Order order){
        Customer customer = this.customerService.getById(order.getCustomer().getId()).getData();
        OrderStatus orderStatus = this.orderStatusService.getById(order.getOrderStatus().getId()).getData();
        Shipment shipment = this.shipmentService.getByOrderId(order.getId()).getData();
        if(shipment != null) {
            return this.automaticMailService.sendOrderStatusMailWithShipment(order, customer, orderStatus, shipment);
        }
        return this.automaticMailService.sendOrderStatusMail(order, customer, orderStatus);
    }

    private DataResult<Boolean> checkCustomerHaveCompany(Customer customer){
        if(!this.companyService.existByCustomerId(customer.getId()).getData()){
            return new ErrorDataResult<>(false,"Müşteriye ait bi şirket bulunamadı");
        }
        return new SuccessDataResult<>(true);
    }
}
