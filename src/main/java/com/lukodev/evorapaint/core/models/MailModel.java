package com.lukodev.evorapaint.core.models;

import com.lukodev.evorapaint.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public class MailModel {

    private String to;
    private String subject;
    private String text;

}
