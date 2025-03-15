package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelCompte;
import com.example.Money.Flow.service.ModelCompteService;
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
@RequestMapping("/api/comptes")
@Tag(name = "Compte API", description = "Endpoints pour la gestion des comptes")
public class CompteController {

    @Autowired
    private ModelCompteService compteService;

    @Operation(summary = "Lister tous les comptes", description = "Retourne la liste de tous les comptes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCompte.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelCompte>> getAllComptes() {
        List<ModelCompte> comptes = compteService.getAllComptes();
        return ResponseEntity.ok(comptes);
    }

    @Operation(summary = "Récupérer un compte par ID", description = "Retourne le compte correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compte trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCompte.class))),
            @ApiResponse(responseCode = "404", description = "Compte non trouvé", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelCompte> getCompteById(@PathVariable Long id) {
        return compteService.getCompteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouveau compte", description = "Crée un compte avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compte créé avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCompte.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelCompte> createCompte(@RequestBody ModelCompte compte) {
        ModelCompte created = compteService.createCompte(compte);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Mettre à jour un compte", description = "Met à jour le compte identifié par l'ID avec les nouvelles informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compte mis à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCompte.class))),
            @ApiResponse(responseCode = "404", description = "Compte non trouvé", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelCompte> updateCompte(@PathVariable Long id, @RequestBody ModelCompte compte) {
        try {
            ModelCompte updated = compteService.updateCompte(id, compte);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un compte", description = "Supprime le compte identifié par l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compte supprimé avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Compte non trouvé", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        try {
            compteService.deleteCompte(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lister les comptes d'un utilisateur", description = "Retourne la liste des comptes associés à un utilisateur donné.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCompte.class)))
    })
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ModelCompte>> getComptesByOwnerId(@PathVariable Long ownerId) {
        List<ModelCompte> comptes = compteService.getComptesByOwnerId(ownerId);
        return ResponseEntity.ok(comptes);
    }

    @Operation(summary = "Rechercher des comptes par libellé", description = "Retourne la liste des comptes correspondant au libellé spécifié.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Résultats trouvés",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCompte.class)))
    })
    @GetMapping("/search")
    public ResponseEntity<List<ModelCompte>> searchByLibelle(@RequestParam String libelle) {
        List<ModelCompte> comptes = compteService.searchByLibelle(libelle);
        return ResponseEntity.ok(comptes);
    }
}
