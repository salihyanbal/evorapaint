package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.CustomerService;
import com.lukodev.evorapaint.business.abstracts.OrderService;
import com.lukodev.evorapaint.business.abstracts.OrderStatusService;
import com.lukodev.evorapaint.business.abstracts.ShipmentService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.services.automaticMail.AutomaticMailService;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.OrderDao;
import com.lukodev.evorapaint.entities.concretes.Customer;
import com.lukodev.evorapaint.entities.concretes.Order;
import com.lukodev.evorapaint.entities.concretes.OrderStatus;
import com.lukodev.evorapaint.entities.concretes.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderManager implements OrderService{

    private OrderDao orderDao;
    private CustomerService customerService;
    private OrderStatusService orderStatusService;
    private AutomaticMailService automaticMailService;
    private ShipmentService shipmentService;
    @Autowired
    public OrderManager(OrderDao orderDao, CustomerService customerService, OrderStatusService orderStatusService, AutomaticMailService automaticMailService, ShipmentService shipmentService) {
        this.orderDao = orderDao;
        this.customerService = customerService;
        this.orderStatusService = orderStatusService;
        this.automaticMailService = automaticMailService;
        this.shipmentService = shipmentService;
    }

    @Transactional
    @CacheEvict(value = "order.getAll",allEntries = true)
    @Override
    public Result add(Order order) {
        this.orderDao.save(order);
        sendOrderStatusMail(order);
        return new SuccessResult(Messages.ORDER_ADDED);
    }

    @Transactional
    @CacheEvict(value = "order.getAll",allEntries = true)
    @Override
    public Result update(Order order) {
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
}
