package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelCategorie;
import com.example.Money.Flow.service.ModelCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private ModelCategorieService categorieService;

    /**
     * GET - Toutes les catégories
     */
    @GetMapping
    public ResponseEntity<List<ModelCategorie>> getAllCategories() {
        List<ModelCategorie> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * GET - Une catégorie par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelCategorie> getCategorieById(@PathVariable Long id) {
        return categorieService.getCategorieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer une catégorie
     */
    @PostMapping
    public ResponseEntity<ModelCategorie> createCategorie(@RequestBody ModelCategorie categorie) {
        ModelCategorie created = categorieService.createCategorie(categorie);
        return ResponseEntity.ok(created);
    }

    /**
     * PUT - Mettre à jour une catégorie
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelCategorie> updateCategorie(@PathVariable Long id, @RequestBody ModelCategorie categorie) {
        try {
            ModelCategorie updated = categorieService.updateCategorie(id, categorie);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer une catégorie
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        try {
            categorieService.deleteCategorie(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * (Optionnel) GET - Catégorie par libellé
     */
    @GetMapping("/libelle/{libelle}")
    public ResponseEntity<ModelCategorie> getCategorieByLibelle(@PathVariable String libelle) {
        return categorieService.getCategorieByLibelle(libelle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
