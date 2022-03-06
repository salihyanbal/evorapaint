package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.UserVerificationByMailService;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.entities.concretes.User;
import com.lukodev.evorapaint.entities.concretes.UserVerificationByMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("/api/userverificationbymails")
@CrossOrigin
public class UserVerificationByMailsController {

    private UserVerificationByMailService userVerificationByMailService;

    @Autowired
    public UserVerificationByMailsController(UserVerificationByMailService userVerificationByMailService) {
        this.userVerificationByMailService = userVerificationByMailService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody UserVerificationByMail userVerificationByMail){
        return ResponseEntity.ok(this.userVerificationByMailService.add(userVerificationByMail));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UserVerificationByMail userVerificationByMail){
        return ResponseEntity.ok(this.userVerificationByMailService.update(userVerificationByMail));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody UserVerificationByMail userVerificationByMail){
        return ResponseEntity.ok(this.userVerificationByMailService.delete(userVerificationByMail));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody UserVerificationByMail userVerificationByMail){
        return ResponseEntity.ok(this.userVerificationByMailService.verify(userVerificationByMail));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.userVerificationByMailService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(this.userVerificationByMailService.getById(id));
    }

    @GetMapping("/getbyuserid")
    public ResponseEntity<?> getByUserId(@RequestParam int userId){
        return ResponseEntity.ok(this.userVerificationByMailService.getByUserId(userId));
    }

    @GetMapping("/isuserverified")
    public ResponseEntity<?> isUserVerified(@RequestParam int userId){
        return ResponseEntity.ok(this.userVerificationByMailService.isUserVerified(userId));
    }
}
