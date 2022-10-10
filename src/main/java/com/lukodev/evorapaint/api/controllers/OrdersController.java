package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.OrderService;
import com.lukodev.evorapaint.entities.concretes.Address;
import com.lukodev.evorapaint.entities.concretes.Order;
import com.lukodev.evorapaint.entities.concretes.PackageType;
import com.lukodev.evorapaint.entities.concretes.ShipmentMethod;
import com.lukodev.evorapaint.entities.dtos.ShoppingCartToOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrdersController {

    private OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Order order){
        return ResponseEntity.ok(this.orderService.add(order));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Order order){
        return ResponseEntity.ok(this.orderService.update(order));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Order order){
        return ResponseEntity.ok(this.orderService.delete(order));
    }

    @PostMapping("/shoppingcarttoorderbyshoppingcartid")
    public ResponseEntity<?> shoppingCartToOrderByShoppingCartId(@RequestBody ShoppingCartToOrderDto shoppingCartToOrderDto){
        return ResponseEntity.ok(this.orderService.shoppingCartToOrderByShoppingCartId(shoppingCartToOrderDto.getShoppingCartId(),
                shoppingCartToOrderDto.getShipmentMethod(), shoppingCartToOrderDto.getAddress(),shoppingCartToOrderDto.isBelongingToCompany()));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/getallbycustomerid")
    public ResponseEntity<?> getAllByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.orderService.getAllByCustomerId(customerId));
    }

    @GetMapping("/getallbyorderstatusid")
    public ResponseEntity<?> getAllByOrderStatusId(@RequestParam int orderStatusId){
        return ResponseEntity.ok(this.orderService.getAllByOrderStatusId(orderStatusId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.orderService.getById(id));
    }

}
