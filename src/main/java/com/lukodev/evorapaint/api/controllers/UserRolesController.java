package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.UserRoleService;
import com.lukodev.evorapaint.entities.concretes.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/userroles")
@CrossOrigin
public class UserRolesController {

    private UserRoleService userRoleService;

    @Autowired
    public UserRolesController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody UserRole userRole){
        return ResponseEntity.ok(this.userRoleService.add(userRole));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UserRole userRole){
        return ResponseEntity.ok(this.userRoleService.update(userRole));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody UserRole userRole){
        return ResponseEntity.ok(this.userRoleService.delete(userRole));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.userRoleService.getAll());
    }

    @GetMapping("/getallbyuserid")
    public ResponseEntity<?> getAllByUserId(@RequestParam int userId){
        return ResponseEntity.ok(this.userRoleService.getAllByUserId(userId));
    }

    @GetMapping("/getallbyroleid")
    public ResponseEntity<?> getAllByRoleId(@RequestParam int roleId){
        return ResponseEntity.ok(this.userRoleService.getAllByRoleId(roleId));
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.userRoleService.getById(id));
    }
}
