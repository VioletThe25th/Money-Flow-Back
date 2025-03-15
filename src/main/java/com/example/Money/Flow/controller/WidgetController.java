package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelWidget;
import com.example.Money.Flow.service.ModelWidgetService;
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
@RequestMapping("/api/widgets")
@Tag(name = "Widget API", description = "Endpoints pour la gestion des widgets")
public class WidgetController {

    @Autowired
    private ModelWidgetService widgetService;

    @Operation(summary = "Lister tous les widgets", description = "Retourne la liste de tous les widgets.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des widgets récupérée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelWidget.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelWidget>> getAllWidgets() {
        List<ModelWidget> widgets = widgetService.getAllWidgets();
        return ResponseEntity.ok(widgets);
    }

    @Operation(summary = "Récupérer un widget par ID", description = "Retourne le widget correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelWidget.class))),
            @ApiResponse(responseCode = "404", description = "Widget non trouvé", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelWidget> getWidgetById(@PathVariable Long id) {
        return widgetService.getWidgetByLibelle(String.valueOf(id)) // Correction éventuelle selon votre service
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un widget", description = "Crée un nouveau widget avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget créé avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelWidget.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelWidget> createWidget(@RequestBody ModelWidget widget) {
        try {
            ModelWidget created = widgetService.createWidget(widget);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Mettre à jour un widget", description = "Met à jour le widget identifié par l'ID avec les nouvelles informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget mis à jour avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelWidget.class))),
            @ApiResponse(responseCode = "404", description = "Widget non trouvé", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelWidget> updateWidget(@PathVariable Long id, @RequestBody ModelWidget widget) {
        try {
            ModelWidget updated = widgetService.updateWidget(id, widget);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer un widget", description = "Supprime le widget identifié par l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Widget supprimé avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Widget non trouvé", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWidget(@PathVariable Long id) {
        try {
            widgetService.deleteWidget(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Rechercher un widget par libellé", description = "Retourne le widget correspondant au libellé fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelWidget.class))),
            @ApiResponse(responseCode = "404", description = "Widget non trouvé", content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<ModelWidget> getWidgetByLibelle(@RequestParam String libelle) {
        return widgetService.getWidgetByLibelle(libelle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
