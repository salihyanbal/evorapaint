package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.RoleService;
import com.lukodev.evorapaint.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RolesController {

    private RoleService roleService;

    @Autowired
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Role role){
        return ResponseEntity.ok(this.roleService.add(role));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Role role){
        return ResponseEntity.ok(this.roleService.update(role));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Role role){
        return ResponseEntity.ok(this.roleService.delete(role));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.roleService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.roleService.getById(id));
    }
}
