package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelBudgetPartage;
import com.example.Money.Flow.service.ModelBudgetPartageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgetpartages")
public class BudgetPartageController {

    @Autowired
    private ModelBudgetPartageService budgetPartageService;

    /**
     * GET - Lister tous les budgets partagés
     */
    @GetMapping
    public ResponseEntity<List<ModelBudgetPartage>> getAllBudgetPartages() {
        List<ModelBudgetPartage> budgetPartages = budgetPartageService.getAllBudgetPartages();
        return ResponseEntity.ok(budgetPartages);
    }

    /**
     * GET - Récupérer un budget partagé par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelBudgetPartage> getBudgetPartageById(@PathVariable Long id) {
        return budgetPartageService.getBudgetPartageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer un budget partagé
     */
    @PostMapping
    public ResponseEntity<ModelBudgetPartage> createBudgetPartage(@RequestBody ModelBudgetPartage budgetPartage) {
        ModelBudgetPartage created = budgetPartageService.createBudgetPartage(budgetPartage);
        return ResponseEntity.ok(created);
    }

    /**
     * PUT - Mettre à jour un budget partagé
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelBudgetPartage> updateBudgetPartage(@PathVariable Long id, @RequestBody ModelBudgetPartage budgetPartage) {
        try {
            ModelBudgetPartage updated = budgetPartageService.updateBudgetPartage(id, budgetPartage);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer un budget partagé
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudgetPartage(@PathVariable Long id) {
        try {
            budgetPartageService.deleteBudgetPartage(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * (Optionnel) GET - Lister les budgets partagés par utilisateur
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ModelBudgetPartage>> getBudgetPartagesByUserId(@PathVariable Long userId) {
        List<ModelBudgetPartage> partages = budgetPartageService.getBudgetPartagesByUserId(userId);
        return ResponseEntity.ok(partages);
    }

    /**
     * (Optionnel) GET - Lister les budgets partagés par compte
     */
    @GetMapping("/compte/{compteId}")
    public ResponseEntity<List<ModelBudgetPartage>> getBudgetPartagesByCompteId(@PathVariable Long compteId) {
        List<ModelBudgetPartage> partages = budgetPartageService.getBudgetPartagesByCompteId(compteId);
        return ResponseEntity.ok(partages);
    }
}
