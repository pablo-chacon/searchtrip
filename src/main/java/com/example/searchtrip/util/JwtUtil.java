package com.example.searchtrip.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    static final String SECRET_KEY = "esomatsaebueogtdcreawutthepasareawooandltetoohefoyareesoetsnotrgetabosomawetrrotsm";
    static final long EXPIRE_IN = 1000 * 60 * 60; // 1h

    public static String sign(String username) {

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts
                .builder()
                .claim("username", username)
                .claim("role", "USER")
                .setExpiration(new Date(System.currentTimeMillis() + 10))
                .signWith(key)
                .compact();
    }

    public static boolean verify(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parse(token) != null;
    }
}
