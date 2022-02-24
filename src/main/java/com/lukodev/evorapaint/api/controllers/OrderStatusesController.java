package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.OrderStatusService;
import com.lukodev.evorapaint.entities.concretes.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderstatuses")
@CrossOrigin
public class OrderStatusesController {

    private OrderStatusService orderStatusService;

    @Autowired
    public OrderStatusesController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody OrderStatus orderStatus){
        return ResponseEntity.ok(this.orderStatusService.add(orderStatus));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody OrderStatus orderStatus){
        return ResponseEntity.ok(this.orderStatusService.update(orderStatus));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody OrderStatus orderStatus){
        return ResponseEntity.ok(this.orderStatusService.delete(orderStatus));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.orderStatusService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.orderStatusService.getById(id));
    }

}
