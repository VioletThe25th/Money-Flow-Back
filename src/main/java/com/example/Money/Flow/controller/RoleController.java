package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelRole;
import com.example.Money.Flow.service.ModelRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role API", description = "Endpoints pour la gestion des rôles")
public class RoleController {

    @Autowired
    private ModelRoleService roleService;

    @Operation(summary = "Lister tous les rôles", description = "Retourne la liste de tous les rôles existants.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des rôles récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelRole.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelRole>> getAllRoles() {
        List<ModelRole> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Récupérer un rôle par ID", description = "Retourne le rôle correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelRole.class))),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelRole> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouveau rôle", description = "Crée un rôle avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle créé avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelRole.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelRole> createRole(@RequestBody ModelRole role) {
        try {
            ModelRole created = roleService.createRole(role);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Mettre à jour un rôle existant", description = "Met à jour le rôle identifié par l'ID avec les nouvelles informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle mis à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelRole.class))),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelRole> updateRole(@PathVariable Long id, @RequestBody ModelRole role) {
        try {
            ModelRole updated = roleService.updateRole(id, role);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un rôle", description = "Supprime le rôle identifié par l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rôle supprimé avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Rechercher un rôle par son nom", description = "Retourne le rôle correspondant au nom fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelRole.class))),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé", content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<ModelRole> getRoleByName(@RequestParam String roleName) {
        return roleService.getRoleByName(roleName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
