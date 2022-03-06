package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.CustomerService;
import com.lukodev.evorapaint.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomersController {

    private CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok(this.customerService.add(customer));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok(this.customerService.update(customer));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Customer customer){
        return ResponseEntity.ok(this.customerService.delete(customer));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.customerService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.customerService.getById(id));
    }
}
