package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.OrderProductService;
import com.lukodev.evorapaint.entities.concretes.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orderproducts")
@CrossOrigin
public class OrderProductsController {

    private OrderProductService orderProductService;

    @Autowired
    public OrderProductsController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody OrderProduct orderProduct){
        return ResponseEntity.ok(this.orderProductService.add(orderProduct));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody OrderProduct orderProduct){
        return ResponseEntity.ok(this.orderProductService.update(orderProduct));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody OrderProduct orderProduct){
        return ResponseEntity.ok(this.orderProductService.delete(orderProduct));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.orderProductService.getAll());
    }

    @GetMapping("/getallbyorderid")
    public ResponseEntity<?> getAllByOrderId(@RequestParam int orderId){
        return ResponseEntity.ok(this.orderProductService.getAllByOrderId(orderId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.orderProductService.getById(id));
    }

}
