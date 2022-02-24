package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "employees")
public class Employee extends User{

    @Column(name = "phone_number")
    @Digits(integer = 20, fraction = 0)
    private String phoneNumber;

    @Column(name = "position")
    private String position;
}
