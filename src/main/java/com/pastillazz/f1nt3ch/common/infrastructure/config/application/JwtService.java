package com.pastillazz.f1nt3ch.common.infrastructure.config.application;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities()
                        .stream()
                        .map(auth->auth
                                .getAuthority().replace("ROLE_", ""))
                        .toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractAllClaims(String token){
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String extractUsername(String token){
    return extractAllClaims(token).getSubject();
    }

    public List<String> extractRoles(String token){
        return extractAllClaims(token).get("roles", List.class);
    }

    public boolean isTokenValid(String token, String username){
        try{
            Claims claims=extractAllClaims(token);
            return claims.getSubject().equals(username) &&
                    !claims.getExpiration().before(new Date());
        }
        catch (Exception e) {
            return false;
        }
        }





}
