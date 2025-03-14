package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelWidgetOwned;
import com.example.Money.Flow.service.ModelWidgetOwnedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/widgets-owned")
public class WidgetOwnedController {

    @Autowired
    private ModelWidgetOwnedService widgetOwnedService;

    /**
     * GET - Lister tous les widgets possédés
     */
    @GetMapping
    public ResponseEntity<List<ModelWidgetOwned>> getAllWidgetsOwned() {
        List<ModelWidgetOwned> widgetsOwned = widgetOwnedService.getAllWidgetsOwned();
        return ResponseEntity.ok(widgetsOwned);
    }

    /**
     * GET - Récupérer un widget possédé par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelWidgetOwned> getWidgetOwnedById(@PathVariable Long id) {
        return widgetOwnedService.getWidgetOwnedById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer un widget possédé
     */
    @PostMapping
    public ResponseEntity<ModelWidgetOwned> createWidgetOwned(@RequestBody ModelWidgetOwned widgetOwned) {
        try {
            ModelWidgetOwned created = widgetOwnedService.createWidgetOwned(widgetOwned);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT - Mettre à jour un widget possédé
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelWidgetOwned> updateWidgetOwned(@PathVariable Long id, @RequestBody ModelWidgetOwned widgetOwned) {
        try {
            ModelWidgetOwned updated = widgetOwnedService.updateWidgetOwned(id, widgetOwned);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer un widget possédé
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWidgetOwned(@PathVariable Long id) {
        try {
            widgetOwnedService.deleteWidgetOwned(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET - Lister les widgets possédés d'un tableau
     */
    @GetMapping("/tableau/{tableauId}")
    public ResponseEntity<List<ModelWidgetOwned>> getWidgetsByTableauId(@PathVariable Long tableauId) {
        List<ModelWidgetOwned> widgets = widgetOwnedService.getWidgetsByTableauId(tableauId);
        return ResponseEntity.ok(widgets);
    }

    /**
     * GET - Lister les widgets possédés par widget ID (optionnel)
     */
    @GetMapping("/widget/{widgetId}")
    public ResponseEntity<List<ModelWidgetOwned>> getWidgetsByWidgetId(@PathVariable Long widgetId) {
        List<ModelWidgetOwned> widgets = widgetOwnedService.getWidgetsByWidgetId(widgetId);
        return ResponseEntity.ok(widgets);
    }
}
