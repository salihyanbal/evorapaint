package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.ShoppingCartService;
import com.lukodev.evorapaint.entities.concretes.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shoppingcarts")
@CrossOrigin
public class ShoppingCartsController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartsController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ShoppingCart shoppingCart){
        return ResponseEntity.ok(this.shoppingCartService.add(shoppingCart));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody ShoppingCart shoppingCart){
        return ResponseEntity.ok(this.shoppingCartService.update(shoppingCart));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ShoppingCart shoppingCart){
        return ResponseEntity.ok(this.shoppingCartService.delete(shoppingCart));
    }

    @PostMapping("/deletebycustomerid")
    public ResponseEntity<?> deleteByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.shoppingCartService.deleteByCustomerId(customerId));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.shoppingCartService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.shoppingCartService.getById(id));
    }

    @GetMapping("/getbycustomerid")
    public ResponseEntity<?> getByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.shoppingCartService.getByCustomerId(customerId));
    }

    @GetMapping("/existbycustomerid")
    public ResponseEntity<?> existByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.shoppingCartService.existByCustomerId(customerId));
    }
}
