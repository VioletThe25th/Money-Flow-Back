package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelWidget;
import com.example.Money.Flow.service.ModelWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/widgets")
public class WidgetController {

    @Autowired
    private ModelWidgetService widgetService;

    /**
     * GET - Liste de tous les widgets
     */
    @GetMapping
    public ResponseEntity<List<ModelWidget>> getAllWidgets() {
        List<ModelWidget> widgets = widgetService.getAllWidgets();
        return ResponseEntity.ok(widgets);
    }

    /**
     * GET - Récupérer un widget par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelWidget> getWidgetById(@PathVariable Long id) {
        return widgetService.getWidgetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer un widget
     */
    @PostMapping
    public ResponseEntity<ModelWidget> createWidget(@RequestBody ModelWidget widget) {
        try {
            ModelWidget created = widgetService.createWidget(widget);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT - Mettre à jour un widget
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelWidget> updateWidget(@PathVariable Long id, @RequestBody ModelWidget widget) {
        try {
            ModelWidget updated = widgetService.updateWidget(id, widget);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer un widget
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWidget(@PathVariable Long id) {
        try {
            widgetService.deleteWidget(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET - Récupérer un widget par libellé
     */
    @GetMapping("/search")
    public ResponseEntity<ModelWidget> getWidgetByLibelle(@RequestParam String libelle) {
        return widgetService.getWidgetByLibelle(libelle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
