package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelWidgetOwned;
import com.example.Money.Flow.service.ModelWidgetOwnedService;
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
@RequestMapping("/api/widgets-owned")
@Tag(name = "Widget Owned API", description = "Endpoints pour la gestion des widgets possédés")
public class WidgetOwnedController {

    @Autowired
    private ModelWidgetOwnedService widgetOwnedService;

    @Operation(summary = "Lister tous les widgets possédés", description = "Retourne la liste de tous les widgets possédés.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelWidgetOwned.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelWidgetOwned>> getAllWidgetsOwned() {
        List<ModelWidgetOwned> widgetsOwned = widgetOwnedService.getAllWidgetsOwned();
        return ResponseEntity.ok(widgetsOwned);
    }

    @Operation(summary = "Récupérer un widget possédé par ID", description = "Retourne le widget possédé correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget possédé trouvé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelWidgetOwned.class))),
            @ApiResponse(responseCode = "404", description = "Widget possédé non trouvé", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelWidgetOwned> getWidgetOwnedById(@PathVariable Long id) {
        return widgetOwnedService.getWidgetOwnedById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un widget possédé", description = "Crée un nouveau widget possédé avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget possédé créé avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelWidgetOwned.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelWidgetOwned> createWidgetOwned(@RequestBody ModelWidgetOwned widgetOwned) {
        try {
            ModelWidgetOwned created = widgetOwnedService.createWidgetOwned(widgetOwned);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Mettre à jour un widget possédé", description = "Met à jour le widget possédé identifié par l'ID avec les nouvelles informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget possédé mis à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelWidgetOwned.class))),
            @ApiResponse(responseCode = "404", description = "Widget possédé non trouvé", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelWidgetOwned> updateWidgetOwned(@PathVariable Long id, @RequestBody ModelWidgetOwned widgetOwned) {
        try {
            ModelWidgetOwned updated = widgetOwnedService.updateWidgetOwned(id, widgetOwned);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un widget possédé", description = "Supprime le widget possédé identifié par l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Widget possédé supprimé avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Widget possédé non trouvé", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWidgetOwned(@PathVariable Long id) {
        try {
            widgetOwnedService.deleteWidgetOwned(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lister les widgets possédés d'un tableau", description = "Retourne la liste des widgets possédés associés à un tableau spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelWidgetOwned.class)))
    })
    @GetMapping("/tableau/{tableauId}")
    public ResponseEntity<List<ModelWidgetOwned>> getWidgetsByTableauId(@PathVariable Long tableauId) {
        List<ModelWidgetOwned> widgets = widgetOwnedService.getWidgetsByTableauId(tableauId);
        return ResponseEntity.ok(widgets);
    }

    @Operation(summary = "Lister les widgets possédés par widget ID", description = "Retourne la liste des widgets possédés associés à un widget spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelWidgetOwned.class)))
    })
    @GetMapping("/widget/{widgetId}")
    public ResponseEntity<List<ModelWidgetOwned>> getWidgetsByWidgetId(@PathVariable Long widgetId) {
        List<ModelWidgetOwned> widgets = widgetOwnedService.getWidgetsByWidgetId(widgetId);
        return ResponseEntity.ok(widgets);
    }
}
