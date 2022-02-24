package com.lukodev.evorapaint.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeForRegisterDto {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String position;

    private boolean active;

}
