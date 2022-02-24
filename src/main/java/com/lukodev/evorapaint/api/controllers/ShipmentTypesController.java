package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.ShipmentTypeService;
import com.lukodev.evorapaint.entities.concretes.ShipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipmenttypes")
@CrossOrigin
public class ShipmentTypesController {

    private ShipmentTypeService shipmentTypeService;

    @Autowired
    public ShipmentTypesController(ShipmentTypeService shipmentTypeService) {
        this.shipmentTypeService = shipmentTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ShipmentType shipmentType){
        return ResponseEntity.ok(this.shipmentTypeService.add(shipmentType));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ShipmentType shipmentType){
        return ResponseEntity.ok(this.shipmentTypeService.update(shipmentType));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody ShipmentType shipmentType){
        return ResponseEntity.ok(this.shipmentTypeService.delete(shipmentType));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.shipmentTypeService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.shipmentTypeService.getById(id));
    }

}
