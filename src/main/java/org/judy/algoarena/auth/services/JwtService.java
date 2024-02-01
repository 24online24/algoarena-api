package org.judy.algoarena.auth.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECRET_KEY = "ODM0ZWUxNTdiZTE2NTk4NDU5YmNmYzczYzllNmM4ZTViZjY0ODhiOWRlMjFmMGZmODAxYWFiNDdlZmFiOGFmZg==";
    private static ArrayList<String> tokenBlacklist = new ArrayList<String>();

    public String getUserEmailFromJwt(String jwt) {
        return extractClaim(jwt, "sub", String.class);
    }

    public boolean isTokenExpired(String jwt) {
        return extractAllClaims(jwt).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        if (tokenBlacklist.contains(jwt)) {
            return false;
        }
        final String email = extractClaim(jwt, "sub", String.class);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(jwt);

    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails.getUsername());
    }

    public <T> T extractClaim(String jwt, String claimName, Class<T> requiredType) {
        Claims claims = extractAllClaims(jwt);
        return claims.get(claimName, requiredType);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void invalidateToken(String token) {
        tokenBlacklist.add(token);
    }
}
