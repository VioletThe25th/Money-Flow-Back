package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelTableau;
import com.example.Money.Flow.service.ModelTableauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/tableaux")
public class TableauController {

    @Autowired
    private ModelTableauService tableauService;

    /**
     * GET - Lister tous les tableaux
     */
    @GetMapping
    public ResponseEntity<List<ModelTableau>> getAllTableaux() {
        List<ModelTableau> tableaux = tableauService.getAllTableaux();
        return ResponseEntity.ok(tableaux);
    }

    /**
     * GET - Récupérer un tableau par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelTableau> getTableauById(@PathVariable Long id) {
        return tableauService.getTableauById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer un tableau
     */
    @PostMapping
    public ResponseEntity<ModelTableau> createTableau(@RequestBody ModelTableau tableau) {
        try {
            ModelTableau created = tableauService.createTableau(tableau);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT - Mettre à jour un tableau
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelTableau> updateTableau(@PathVariable Long id, @RequestBody ModelTableau tableau) {
        try {
            ModelTableau updated = tableauService.updateTableau(id, tableau);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer un tableau
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableau(@PathVariable Long id) {
        try {
            tableauService.deleteTableau(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET - Récupérer un tableau par l'ID de l'owner
     */
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<ModelTableau> getTableauByOwnerId(@PathVariable Long ownerId) {
        return tableauService.getTableauByOwnerId(BigInteger.valueOf(ownerId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
