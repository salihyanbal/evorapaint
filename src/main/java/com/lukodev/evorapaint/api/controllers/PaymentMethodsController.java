package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.PaymentMethodService;
import com.lukodev.evorapaint.entities.concretes.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paymentmethods")
@CrossOrigin
public class PaymentMethodsController {

    private PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodsController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PaymentMethod paymentMethod){
        return ResponseEntity.ok(this.paymentMethodService.add(paymentMethod));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody PaymentMethod paymentMethod){
        return ResponseEntity.ok(this.paymentMethodService.update(paymentMethod));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody PaymentMethod paymentMethod){
        return ResponseEntity.ok(this.paymentMethodService.delete(paymentMethod));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.paymentMethodService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.paymentMethodService.getById(id));
    }

}
