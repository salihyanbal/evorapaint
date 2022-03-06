package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.AddressService;
import com.lukodev.evorapaint.entities.concretes.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin
public class AddressesController {

    private AddressService addressService;

    @Autowired
    public AddressesController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Address address){
        return ResponseEntity.ok(this.addressService.add(address));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Address address){
        return ResponseEntity.ok(this.addressService.update(address));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Address address){
        return ResponseEntity.ok(this.addressService.delete(address));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.addressService.getAll());
    }

    @GetMapping("/getallbycustomerid")
    public ResponseEntity<?> getAllByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.addressService.getAllByCustomerId(customerId));
    }

    @GetMapping("/getallbycustomeridanddeletedfalse")
    public ResponseEntity<?> getAllByCustomerIdAndDeletedFalse(@RequestParam int customerId){
        return ResponseEntity.ok(this.addressService.getAllByCustomerIdAndDeletedFalse(customerId));
    }


    @GetMapping("/getallbypagenumber")
    public ResponseEntity<?> getAllByPageNumber(@RequestParam int pageNumber){
        return ResponseEntity.ok(this.addressService.getAllByPageNumber(pageNumber));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.addressService.getById(id));
    }

}
