package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.UserService;
import com.lukodev.evorapaint.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.add(user));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.update(user));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody User user){
        return ResponseEntity.ok(this.userService.delete(user));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.userService.getById(id));
    }

}

