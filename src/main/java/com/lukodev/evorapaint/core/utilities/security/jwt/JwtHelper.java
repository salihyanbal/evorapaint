package com.lukodev.evorapaint.core.utilities.security.jwt;

import com.lukodev.evorapaint.core.utilities.security.constants.ClaimTypes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtHelper implements TokenHelper{

    private byte[] SECRET_KEY = "evorapaint_jwt_secretkey".getBytes(StandardCharsets.UTF_8);
    private int accessTokenExpirationInMinutes = 10;
    private int refreshTokenExpirationInMinutes = 60;

    @Override
    public int extractUserId(String token) {
        return (int) extractAllClaims(token).get(ClaimTypes.USER_IDENTIFIER);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    @Override
    public List<String> claimRoles(String token){
        return (List<String>) Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get(SECRET_KEY);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String createAccessToken(List<String> roles, String subject, int userId) {
        return Jwts.builder().
                claim(ClaimTypes.AUTHORITIES_KEY, roles)
                .claim(ClaimTypes.USER_IDENTIFIER, userId)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationInMinutes * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public String createRefreshToken(String subject, int userId) {
        return Jwts.builder()
                .claim(ClaimTypes.USER_IDENTIFIER, userId)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationInMinutes * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
