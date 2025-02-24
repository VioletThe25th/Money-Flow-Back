package com.example.Money.Flow.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;
    private final int jwtExpirationMs = 86400000; // 1 jour

    // Génère un token à partir de l'objet Authentication
    public String generateJwtToken(org.springframework.security.core.Authentication authentication) {
        String username = authentication.getName();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Récupère le username contenu dans le token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Valide le token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            // Clé invalide
        } catch (MalformedJwtException e) {
            // Token malformé
        } catch (ExpiredJwtException e) {
            // Token expiré
        } catch (UnsupportedJwtException e) {
            // Type de token non supporté
        } catch (IllegalArgumentException e) {
            // Chaîne vide ou nulle
        }
        return false;
    }
}
