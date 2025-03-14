package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelClassification;
import com.example.Money.Flow.service.ModelClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classifications")
public class ClassificationController {

    @Autowired
    private ModelClassificationService classificationService;

    /**
     * GET - Liste toutes les classifications
     */
    @GetMapping
    public ResponseEntity<List<ModelClassification>> getAllClassifications() {
        List<ModelClassification> classifications = classificationService.getAllClassifications();
        return ResponseEntity.ok(classifications);
    }

    /**
     * GET - Récupérer une classification par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelClassification> getClassificationById(@PathVariable Long id) {
        return classificationService.getClassificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer une nouvelle classification
     */
    @PostMapping
    public ResponseEntity<ModelClassification> createClassification(@RequestBody ModelClassification classification) {
        ModelClassification created = classificationService.createClassification(classification);
        return ResponseEntity.ok(created);
    }

    /**
     * PUT - Mettre à jour une classification existante
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelClassification> updateClassification(@PathVariable Long id, @RequestBody ModelClassification classification) {
        try {
            ModelClassification updated = classificationService.updateClassification(id, classification);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer une classification par ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable Long id) {
        try {
            classificationService.deleteClassification(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * (Optionnel) GET - Liste des classifications d'une transaction
     */
    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<List<ModelClassification>> getClassificationsByTransactionId(@PathVariable Long transactionId) {
        List<ModelClassification> classifications = classificationService.getClassificationsByTransactionId(transactionId);
        return ResponseEntity.ok(classifications);
    }

    /**
     * (Optionnel) GET - Liste des classifications d'une catégorie
     */
    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<ModelClassification>> getClassificationsByCategorieId(@PathVariable Long categorieId) {
        List<ModelClassification> classifications = classificationService.getClassificationsByCategorieId(categorieId);
        return ResponseEntity.ok(classifications);
    }
}
