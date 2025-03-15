package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelCategorie;
import com.example.Money.Flow.service.ModelCategorieService;
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
@RequestMapping("/api/categories")
@Tag(name = "Categorie API", description = "Endpoints pour la gestion des catégories")
public class CategorieController {

    @Autowired
    private ModelCategorieService categorieService;

    @Operation(summary = "Obtenir toutes les catégories", description = "Retourne la liste de toutes les catégories disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCategorie.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelCategorie>> getAllCategories() {
        List<ModelCategorie> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Obtenir une catégorie par ID", description = "Retourne la catégorie correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCategorie.class))),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelCategorie> getCategorieById(@PathVariable Long id) {
        return categorieService.getCategorieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer une catégorie", description = "Crée une nouvelle catégorie avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie créée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCategorie.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelCategorie> createCategorie(@RequestBody ModelCategorie categorie) {
        ModelCategorie created = categorieService.createCategorie(categorie);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Mettre à jour une catégorie", description = "Met à jour la catégorie identifiée par l'ID avec les nouvelles informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCategorie.class))),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelCategorie> updateCategorie(@PathVariable Long id, @RequestBody ModelCategorie categorie) {
        try {
            ModelCategorie updated = categorieService.updateCategorie(id, categorie);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une catégorie", description = "Supprime la catégorie identifiée par l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Catégorie supprimée avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        try {
            categorieService.deleteCategorie(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtenir une catégorie par libellé", description = "Retourne la catégorie correspondant au libellé fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelCategorie.class))),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", content = @Content)
    })
    @GetMapping("/libelle/{libelle}")
    public ResponseEntity<ModelCategorie> getCategorieByLibelle(@PathVariable String libelle) {
        return categorieService.getCategorieByLibelle(libelle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
