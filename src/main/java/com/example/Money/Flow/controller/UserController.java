package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.service.ModelUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Endpoints pour la gestion des utilisateurs")
public class UserController {

    @Autowired
    private ModelUserService userService;

    @Operation(summary = "Obtenir tous les utilisateurs", description = "Retourne la liste de tous les utilisateurs enregistrés.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelUser.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelUser>> getAllUsers() {
        List<ModelUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Obtenir un utilisateur par ID", description = "Retourne l'utilisateur correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelUser.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelUser> getUserById(@PathVariable BigInteger id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouvel utilisateur", description = "Crée un utilisateur avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelUser.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelUser> createUser(@RequestBody ModelUser user) {
        try {
            ModelUser newUser = userService.createUser(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Mettre à jour un utilisateur", description = "Met à jour l'utilisateur identifié par l'ID avec les nouvelles informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelUser.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelUser> updateUser(@PathVariable BigInteger id, @RequestBody ModelUser user) {
        try {
            ModelUser updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un utilisateur", description = "Supprime l'utilisateur identifié par l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Utilisateur supprimé avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable BigInteger id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
