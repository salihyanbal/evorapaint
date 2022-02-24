package com.lukodev.evorapaint.core.utilities.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenModel {

    private String accessToken;
    private String refreshToken;

}
