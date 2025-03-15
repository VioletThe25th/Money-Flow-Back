package com.example.Money.Flow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Home API", description = "Endpoints publics pour l'accueil et la zone sécurisée")
public class HomeController {

    @Operation(summary = "Page d'accueil", description = "Renvoie un message d'accueil public")
    @GetMapping("/")
    public String home() {
        return "Welcome";
    }

    @Operation(summary = "Zone sécurisée", description = "Renvoie un message pour les utilisateurs authentifiés, c'était une route test qui servait à vérifier la nécessité du token JWT")
    @GetMapping("/secured")
    public String secured() {
        return "Secured";
    }
}
