package com.lukodev.evorapaint.api.controllers;

import com.lukodev.evorapaint.business.abstracts.AuthService;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.User;
import com.lukodev.evorapaint.entities.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registerforemployee")
    public ResponseEntity<?> registerForEmployee(@Valid  @RequestBody EmployeeForRegisterDto employeeForRegisterDto){
        Result userExist = authService.userExist(employeeForRegisterDto.getEmail());
        if(userExist.isSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userExist);
        }
        var registerResult = authService.registerForEmployee(employeeForRegisterDto);
        var tokensResult = authService.generateTokens(registerResult.getData());
        if(tokensResult.isSuccess()){
            return ResponseEntity.ok(tokensResult);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokensResult);
    }

    @PostMapping("/registerforcustomer")
    public ResponseEntity<?> registerForCustomer(@Valid @RequestBody CustomerForRegisterDto customerForRegisterDto){
        Result userExist = authService.userExist(customerForRegisterDto.getEmail());
        if(userExist.isSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userExist);
        }
        var registerResult = authService.registerForCustomer(customerForRegisterDto);
        var tokensResult = authService.generateTokens(registerResult.getData());
        if(tokensResult.isSuccess()){
            return ResponseEntity.ok(tokensResult);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokensResult);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserForLoginDto userForLoginDto){
        DataResult<User> loginResult = authService.login(userForLoginDto);
        if(!loginResult.isSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResult);
        }
        var tokensResult = authService.generateTokens(loginResult.getData());
        if(tokensResult.isSuccess()){
            return ResponseEntity.ok(tokensResult);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokensResult);
    }

    @PostMapping("/changeemail")
    public ResponseEntity<?> changeEmail(@RequestBody ChangeEmailDto changeEmailDto){
        return ResponseEntity.ok(this.authService.changeEmail(changeEmailDto));
    }

    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto){
        return ResponseEntity.ok(this.authService.changePassword(changePasswordDto));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken){
        return ResponseEntity.ok(this.authService.refreshTokens(refreshToken));
    }
}

