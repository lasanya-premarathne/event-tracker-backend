package com.example.events_tracker_backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
//public class JwtUtil {
//
//    // Ideally load from application.properties
//    private final String SECRET = "whyIsThisSoHard_whyIsThisSoHard"; // must be at least 32 chars
//    private final long EXPIRATION_MS = 3600000; // 1 hour
//
//    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    public String generateToken(String username) {
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        Date expiry = new Date(nowMillis + EXPIRATION_MS);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiry)
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return parseClaims(token).getSubject();
//    }
//
//    public boolean validateToken(String token, String username) {
//        String extractedUsername = extractUsername(token);
//        return (extractedUsername.equals(username) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return parseClaims(token).getExpiration().before(new Date());
//    }
//
//    private Claims parseClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}

public class JwtUtil {

    private static final String SECRET = "whyIsThisSoHardwhyIsThisSoHardwhyIsThisSoHard"; // 32+ chars
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private final long expirationMs = 3600000; // 1 hour

    public String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username);
        } catch (JwtException e) {
            return false; // token invalid
        }
    }
}
