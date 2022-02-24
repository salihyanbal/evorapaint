package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.EmployeeService;
import com.lukodev.evorapaint.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeesController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Employee employee){
        return ResponseEntity.ok(this.employeeService.add(employee));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Employee employee){
        return ResponseEntity.ok(this.employeeService.update(employee));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Employee employee){
        return ResponseEntity.ok(this.employeeService.delete(employee));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.employeeService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.employeeService.getById(id));
    }
}
