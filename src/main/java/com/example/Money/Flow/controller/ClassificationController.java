package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelClassification;
import com.example.Money.Flow.service.ModelClassificationService;
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
@RequestMapping("/api/classifications")
@Tag(name = "Classification API", description = "Endpoints pour la gestion des classifications")
public class ClassificationController {

    @Autowired
    private ModelClassificationService classificationService;

    @Operation(summary = "Lister toutes les classifications", description = "Retourne la liste de toutes les classifications disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des classifications récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelClassification.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelClassification>> getAllClassifications() {
        List<ModelClassification> classifications = classificationService.getAllClassifications();
        return ResponseEntity.ok(classifications);
    }

    @Operation(summary = "Récupérer une classification par ID", description = "Retourne la classification correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classification trouvée",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelClassification.class))),
            @ApiResponse(responseCode = "404", description = "Classification non trouvée", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelClassification> getClassificationById(@PathVariable Long id) {
        return classificationService.getClassificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer une classification", description = "Crée une nouvelle classification avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classification créée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelClassification.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelClassification> createClassification(@RequestBody ModelClassification classification) {
        ModelClassification created = classificationService.createClassification(classification);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Mettre à jour une classification", description = "Met à jour la classification identifiée par l'ID avec les nouvelles informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classification mise à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelClassification.class))),
            @ApiResponse(responseCode = "404", description = "Classification non trouvée", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelClassification> updateClassification(@PathVariable Long id, @RequestBody ModelClassification classification) {
        try {
            ModelClassification updated = classificationService.updateClassification(id, classification);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une classification", description = "Supprime la classification identifiée par l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Classification supprimée avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Classification non trouvée", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable Long id) {
        try {
            classificationService.deleteClassification(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lister les classifications d'une transaction", description = "Retourne la liste des classifications associées à une transaction donnée.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelClassification.class)))
    })
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<List<ModelClassification>> getClassificationsByTransactionId(@PathVariable Long transactionId) {
        List<ModelClassification> classifications = classificationService.getClassificationsByTransactionId(transactionId);
        return ResponseEntity.ok(classifications);
    }

    @Operation(summary = "Lister les classifications d'une catégorie", description = "Retourne la liste des classifications associées à une catégorie donnée.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelClassification.class)))
    })
    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<ModelClassification>> getClassificationsByCategorieId(@PathVariable Long categorieId) {
        List<ModelClassification> classifications = classificationService.getClassificationsByCategorieId(categorieId);
        return ResponseEntity.ok(classifications);
    }
}
