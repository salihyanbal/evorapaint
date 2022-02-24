package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.ShipmentMethodService;
import com.lukodev.evorapaint.entities.concretes.ShipmentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipmentmethods")
@CrossOrigin
public class ShipmentMethodsController {

    private ShipmentMethodService shipmentMethodService;

    @Autowired
    public ShipmentMethodsController(ShipmentMethodService shipmentMethodService) {
        this.shipmentMethodService = shipmentMethodService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ShipmentMethod shipmentMethod){
        return ResponseEntity.ok(this.shipmentMethodService.add(shipmentMethod));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ShipmentMethod shipmentMethod){
        return ResponseEntity.ok(this.shipmentMethodService.update(shipmentMethod));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ShipmentMethod shipmentMethod){
        return ResponseEntity.ok(this.shipmentMethodService.delete(shipmentMethod));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.shipmentMethodService.getAll());
    }

    @GetMapping("/getallbyshipmenttypeid")
    public ResponseEntity<?> getAllByShipmentTypeId(@RequestParam int shipmentTypeId){
        return ResponseEntity.ok(this.shipmentMethodService.getAllByShipmentTypeId(shipmentTypeId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.shipmentMethodService.getById(id));
    }

}
