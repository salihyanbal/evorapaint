package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.CompanyService;
import com.lukodev.evorapaint.entities.concretes.Category;
import com.lukodev.evorapaint.entities.concretes.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin
public class CompaniesController {

    private CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Company company){
        return ResponseEntity.ok(this.companyService.add(company));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Company company){
        return ResponseEntity.ok(this.companyService.update(company));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Company company){
        return ResponseEntity.ok(this.companyService.delete(company));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.companyService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.companyService.getById(id));
    }

    @GetMapping("/getbycustomerid")
    public ResponseEntity<?> getByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.companyService.getByCustomerId(customerId));
    }

    @GetMapping("/existbycustomerid")
    public ResponseEntity<?> existByCustomerId(@RequestParam int customerId){
        return ResponseEntity.ok(this.companyService.existByCustomerId(customerId));
    }
}
