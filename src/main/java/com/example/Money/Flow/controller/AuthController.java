package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.payload.JwtResponse;
import com.example.Money.Flow.payload.LoginRequest;
import com.example.Money.Flow.payload.SignupRequest;
import com.example.Money.Flow.security.JwtUtils;
import com.example.Money.Flow.service.ModelUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description = "Endpoints for user authentication and registration")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelUserService userService;

    @Operation(summary = "Authenticate user", description = "Authenticates a user using email and password, and returns a JWT token upon successful authentication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authentication using email as identifier
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @Operation(summary = "Register new user", description = "Registers a new user with the provided details. A default role is assigned if none is provided.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully registered", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        ModelUser user = new ModelUser();
        user.setNom(signupRequest.getNom());
        user.setPrenom(signupRequest.getPrenom());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        // Here, the service will assign a default role if necessary
        userService.createUser(user);
        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }
}
