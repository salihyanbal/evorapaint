package com.lukodev.evorapaint.core.utilities.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public interface TokenHelper {

    int extractUserId(String token);

    String extractUsername(String token);

    Date extractExpiration(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    List<String> claimRoles(String token);

    Boolean isTokenExpired(String token);

    String createAccessToken(List<String> roles, String subject, int userId);

    String createRefreshToken(String subject, int userId);

    Boolean validateToken(String token, UserDetails userDetails);
}
