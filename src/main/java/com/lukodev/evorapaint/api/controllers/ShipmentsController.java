package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.ShipmentService;
import com.lukodev.evorapaint.entities.concretes.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin
public class ShipmentsController {

    private ShipmentService shipmentService;

    @Autowired
    public ShipmentsController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Shipment shipment){
        return ResponseEntity.ok(this.shipmentService.add(shipment));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Shipment shipment){
        return ResponseEntity.ok(this.shipmentService.update(shipment));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Shipment shipment){
        return ResponseEntity.ok(this.shipmentService.delete(shipment));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.shipmentService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.shipmentService.getById(id));
    }

    @GetMapping("/getbyorderid")
    public ResponseEntity<?> getByOrderId(@RequestParam int orderId){
        return ResponseEntity.ok(this.shipmentService.getByOrderId(orderId));
    }

}
