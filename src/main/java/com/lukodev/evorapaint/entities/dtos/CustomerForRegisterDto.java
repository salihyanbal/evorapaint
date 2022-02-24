package com.lukodev.evorapaint.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lukodev.evorapaint.entities.concretes.Address;
import com.lukodev.evorapaint.entities.concretes.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerForRegisterDto {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String mainCountry;

    private String phoneNumber;

    private boolean active;

}
