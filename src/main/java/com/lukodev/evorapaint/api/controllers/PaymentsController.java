package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.PaymentService;
import com.lukodev.evorapaint.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentsController {

    private PaymentService paymentService;

    @Autowired
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Payment payment){
        return ResponseEntity.ok(this.paymentService.add(payment));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Payment payment){
        return ResponseEntity.ok(this.paymentService.update(payment));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Payment payment){
        return ResponseEntity.ok(this.paymentService.delete(payment));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.paymentService.getAll());
    }

    @GetMapping("/getallbyorderid")
    public ResponseEntity<?> getAllByOrderId(@RequestParam int orderId){
        return ResponseEntity.ok(this.paymentService.getAllByOrderId(orderId));
    }

    @GetMapping("/getallbycustomerid")
    public ResponseEntity<?> getAllByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.paymentService.getAllByCustomerId(customerId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.paymentService.getById(id));
    }

}
