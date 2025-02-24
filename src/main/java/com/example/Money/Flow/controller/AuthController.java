package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.payload.JwtResponse;
import com.example.Money.Flow.payload.LoginRequest;
import com.example.Money.Flow.payload.SignupRequest;
import com.example.Money.Flow.security.JwtUtils;
import com.example.Money.Flow.service.ModelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
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

    @Autowired
    private ModelUserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authentification en utilisant l'email comme identifiant
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        ModelUser user = new ModelUser();
        user.setNom(signupRequest.getNom());
        user.setPrenom(signupRequest.getPrenom());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        // Vous pouvez gérer ici le choix du rôle (par exemple, récupérer un ModelRole par défaut)
        userService.registerUser(user);
        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }
}
