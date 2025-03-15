package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelBudgetPartage;
import com.example.Money.Flow.service.ModelBudgetPartageService;
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
@RequestMapping("/api/budgetpartages")
@Tag(name = "Budget Partagé API", description = "Endpoints pour la gestion des budgets partagés")
public class BudgetPartageController {

    @Autowired
    private ModelBudgetPartageService budgetPartageService;

    @Operation(summary = "Lister tous les budgets partagés", description = "Retourne la liste de tous les budgets partagés.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelBudgetPartage.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelBudgetPartage>> getAllBudgetPartages() {
        List<ModelBudgetPartage> budgetPartages = budgetPartageService.getAllBudgetPartages();
        return ResponseEntity.ok(budgetPartages);
    }

    @Operation(summary = "Récupérer un budget partagé par ID", description = "Retourne un budget partagé en fonction de son identifiant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Budget partagé trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelBudgetPartage.class))),
            @ApiResponse(responseCode = "404", description = "Budget partagé non trouvé", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelBudgetPartage> getBudgetPartageById(@PathVariable Long id) {
        return budgetPartageService.getBudgetPartageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un budget partagé", description = "Crée un nouveau budget partagé avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Budget partagé créé avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelBudgetPartage.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelBudgetPartage> createBudgetPartage(@RequestBody ModelBudgetPartage budgetPartage) {
        ModelBudgetPartage created = budgetPartageService.createBudgetPartage(budgetPartage);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Mettre à jour un budget partagé", description = "Met à jour les informations d'un budget partagé existant identifié par son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Budget partagé mis à jour",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelBudgetPartage.class))),
            @ApiResponse(responseCode = "404", description = "Budget partagé non trouvé", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelBudgetPartage> updateBudgetPartage(@PathVariable Long id, @RequestBody ModelBudgetPartage budgetPartage) {
        try {
            ModelBudgetPartage updated = budgetPartageService.updateBudgetPartage(id, budgetPartage);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un budget partagé", description = "Supprime un budget partagé identifié par son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Budget partagé supprimé avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Budget partagé non trouvé", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudgetPartage(@PathVariable Long id) {
        try {
            budgetPartageService.deleteBudgetPartage(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lister les budgets partagés par utilisateur", description = "Retourne la liste des budgets partagés associés à un utilisateur donné.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelBudgetPartage.class)))
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ModelBudgetPartage>> getBudgetPartagesByUserId(@PathVariable Long userId) {
        List<ModelBudgetPartage> partages = budgetPartageService.getBudgetPartagesByUserId(userId);
        return ResponseEntity.ok(partages);
    }

    @Operation(summary = "Lister les budgets partagés par compte", description = "Retourne la liste des budgets partagés associés à un compte donné.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelBudgetPartage.class)))
    })
    @GetMapping("/compte/{compteId}")
    public ResponseEntity<List<ModelBudgetPartage>> getBudgetPartagesByCompteId(@PathVariable Long compteId) {
        List<ModelBudgetPartage> partages = budgetPartageService.getBudgetPartagesByCompteId(compteId);
        return ResponseEntity.ok(partages);
    }
}
