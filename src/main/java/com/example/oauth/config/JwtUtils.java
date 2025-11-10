package com.example.oauth.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtils {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String ROLE = "role";

    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim(ROLE, role)
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(key)
                .compact();
    }

    public static String validateAndGetUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get(ROLE, String.class);
    }
}
