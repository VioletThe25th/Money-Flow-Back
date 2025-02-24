package com.example.Money.Flow.controller;

import com.example.Money.Flow.payload.JwtResponse;
import com.example.Money.Flow.payload.LoginRequest;
import com.example.Money.Flow.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authentifier l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Placer l'authentification dans le contexte de sécurité
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Générer le token JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Retourner le token dans la réponse
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
