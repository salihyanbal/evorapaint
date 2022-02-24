package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.RemittanceInformationService;
import com.lukodev.evorapaint.entities.concretes.RemittanceInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/remittanceinformations")
@CrossOrigin
public class RemittanceInformationsController {

    private RemittanceInformationService remittanceInformationService;

    @Autowired
    public RemittanceInformationsController(RemittanceInformationService remittanceInformationService) {
        this.remittanceInformationService = remittanceInformationService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody RemittanceInformation remittanceInformation){
        return ResponseEntity.ok(this.remittanceInformationService.add(remittanceInformation));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody RemittanceInformation remittanceInformation){
        return ResponseEntity.ok(this.remittanceInformationService.update(remittanceInformation));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody RemittanceInformation remittanceInformation){
        return ResponseEntity.ok(this.remittanceInformationService.delete(remittanceInformation));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.remittanceInformationService.getAll());
    }

    @GetMapping("/getallbypaymentmethodid")
    public ResponseEntity<?> getAllByPaymentMethodId(@RequestParam int paymentMethodId){
        return ResponseEntity.ok(this.remittanceInformationService.getAllByPaymentMethodId(paymentMethodId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.remittanceInformationService.getById(id));
    }

}
