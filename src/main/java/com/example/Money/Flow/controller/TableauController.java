package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelTableau;
import com.example.Money.Flow.service.ModelTableauService;
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
@RequestMapping("/api/tableaux")
@Tag(name = "Tableau API", description = "Endpoints pour la gestion des tableaux")
public class TableauController {

    @Autowired
    private ModelTableauService tableauService;

    @Operation(summary = "Lister tous les tableaux", description = "Retourne la liste de tous les tableaux disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des tableaux récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTableau.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelTableau>> getAllTableaux() {
        List<ModelTableau> tableaux = tableauService.getAllTableaux();
        return ResponseEntity.ok(tableaux);
    }

    @Operation(summary = "Récupérer un tableau par ID", description = "Retourne le tableau correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tableau trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTableau.class))),
            @ApiResponse(responseCode = "404", description = "Tableau non trouvé", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelTableau> getTableauById(@PathVariable Long id) {
        return tableauService.getTableauById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un tableau", description = "Crée un nouveau tableau avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tableau créé avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTableau.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelTableau> createTableau(@RequestBody ModelTableau tableau) {
        try {
            ModelTableau created = tableauService.createTableau(tableau);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Mettre à jour un tableau", description = "Met à jour le tableau identifié par l'ID avec les nouvelles informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tableau mis à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTableau.class))),
            @ApiResponse(responseCode = "404", description = "Tableau non trouvé", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelTableau> updateTableau(@PathVariable Long id, @RequestBody ModelTableau tableau) {
        try {
            ModelTableau updated = tableauService.updateTableau(id, tableau);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un tableau", description = "Supprime le tableau identifié par l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tableau supprimé avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tableau non trouvé", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableau(@PathVariable Long id) {
        try {
            tableauService.deleteTableau(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Récupérer un tableau par l'ID de l'owner", description = "Retourne le tableau associé à un owner spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tableau trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTableau.class))),
            @ApiResponse(responseCode = "404", description = "Tableau non trouvé", content = @Content)
    })
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<ModelTableau> getTableauByOwnerId(@PathVariable Long ownerId) {
        return tableauService.getTableauByOwnerId(BigInteger.valueOf(ownerId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
