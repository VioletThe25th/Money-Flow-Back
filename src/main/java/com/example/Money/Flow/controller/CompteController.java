package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelCompte;
import com.example.Money.Flow.service.ModelCompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private ModelCompteService compteService;

    /**
     * GET - Lister tous les comptes
     */
    @GetMapping
    public ResponseEntity<List<ModelCompte>> getAllComptes() {
        List<ModelCompte> comptes = compteService.getAllComptes();
        return ResponseEntity.ok(comptes);
    }

    /**
     * GET - Récupérer un compte par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelCompte> getCompteById(@PathVariable Long id) {
        return compteService.getCompteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer un nouveau compte
     */
    @PostMapping
    public ResponseEntity<ModelCompte> createCompte(@RequestBody ModelCompte compte) {
        ModelCompte created = compteService.createCompte(compte);
        return ResponseEntity.ok(created);
    }

    /**
     * PUT - Mettre à jour un compte
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelCompte> updateCompte(@PathVariable Long id, @RequestBody ModelCompte compte) {
        try {
            ModelCompte updated = compteService.updateCompte(id, compte);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer un compte
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        try {
            compteService.deleteCompte(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET - Lister les comptes d'un utilisateur
     */
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ModelCompte>> getComptesByOwnerId(@PathVariable Long ownerId) {
        List<ModelCompte> comptes = compteService.getComptesByOwnerId(ownerId);
        return ResponseEntity.ok(comptes);
    }

    /**
     * GET - Recherche par libellé
     */
    @GetMapping("/search")
    public ResponseEntity<List<ModelCompte>> searchByLibelle(@RequestParam String libelle) {
        List<ModelCompte> comptes = compteService.searchByLibelle(libelle);
        return ResponseEntity.ok(comptes);
    }
}
