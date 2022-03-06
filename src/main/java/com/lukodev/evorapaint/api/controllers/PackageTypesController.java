package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.PackageTypeService;
import com.lukodev.evorapaint.entities.concretes.PackageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/packagetypes")
@CrossOrigin
public class PackageTypesController {

    private PackageTypeService packageTypeService;

    @Autowired
    public PackageTypesController(PackageTypeService packageTypeService) {
        this.packageTypeService = packageTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PackageType packageType){
        return ResponseEntity.ok(this.packageTypeService.add(packageType));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody PackageType packageType){
        return ResponseEntity.ok(this.packageTypeService.update(packageType));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody PackageType packageType){
        return ResponseEntity.ok(this.packageTypeService.delete(packageType));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.packageTypeService.getAll());
    }

    @GetMapping("/getallbyshipmenttypeid")
    public ResponseEntity<?> getAllByShipmentTypeId(@RequestParam int shipmentTypeId){
        return ResponseEntity.ok(this.packageTypeService.getAllByShipmentTypeId(shipmentTypeId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.packageTypeService.getById(id));
    }

}
