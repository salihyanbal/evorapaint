package com.lukodev.evorapaint.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserForLoginDto {

    private String email;

    private String password;

}
