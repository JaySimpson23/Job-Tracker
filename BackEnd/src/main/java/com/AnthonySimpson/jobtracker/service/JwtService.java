package com.AnthonySimpson.jobtracker.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    // writing method to link who the token is about
    // seeing the timestap it was created
    // when will the timestamp become expired
    // Use my signature key to prove it was not modified

    public String generateToken(String email) {
        return Jwts.builder()
        .subject(email)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) /* 1 hour */
        .signWith(getSigningKey()).compact();
    }
    // writing helper method so the JJWT can convert the String secret in to a cryptographic Key. 

    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Now its time to filter if the incoming tokens are expired or not
    // Doing this by taking in a token 
    //parsing it witht the SAME signing key
    //If succeded, it will show the email of who is making the request, if not throw an expception

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
        .verifyWith((SecretKey) getSigningKey())
        .build().parseSignedClaims(token)
        .getPayload();

        return claims.getSubject();
    }
}
