package com.lukodev.evorapaint.entities.dtos;

import com.lukodev.evorapaint.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailDto {

    private User user;
    private String newEmail;

}
