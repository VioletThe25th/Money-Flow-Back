package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelTransactionProgrammee;
import com.example.Money.Flow.service.ModelTransactionProgrammeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/transactions-programmees")
public class TransactionProgrammeeController {

    @Autowired
    private ModelTransactionProgrammeeService transactionProgrammeeService;

    /**
     * GET - Liste toutes les transactions programmées
     */
    @GetMapping
    public ResponseEntity<List<ModelTransactionProgrammee>> getAllTransactionsProgrammee() {
        List<ModelTransactionProgrammee> transactions = transactionProgrammeeService.getAllTransactionsProgrammee();
        return ResponseEntity.ok(transactions);
    }

    /**
     * GET - Récupérer une transaction programmée par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelTransactionProgrammee> getTransactionProgrammeeById(@PathVariable Long id) {
        return transactionProgrammeeService.getTransactionProgrammeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer une transaction programmée
     */
    @PostMapping
    public ResponseEntity<ModelTransactionProgrammee> createTransactionProgrammee(@RequestBody ModelTransactionProgrammee transaction) {
        try {
            ModelTransactionProgrammee created = transactionProgrammeeService.createTransactionProgrammee(transaction);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT - Mettre à jour une transaction programmée
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelTransactionProgrammee> updateTransactionProgrammee(@PathVariable Long id, @RequestBody ModelTransactionProgrammee transaction) {
        try {
            ModelTransactionProgrammee updated = transactionProgrammeeService.updateTransactionProgrammee(id, transaction);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer une transaction programmée
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionProgrammee(@PathVariable Long id) {
        try {
            transactionProgrammeeService.deleteTransactionProgrammee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET - Transactions programmées par owner
     */
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ModelTransactionProgrammee>> getTransactionsByOwnerId(@PathVariable Long ownerId) {
        List<ModelTransactionProgrammee> transactions = transactionProgrammeeService.getTransactionsByOwnerId(ownerId);
        return ResponseEntity.ok(transactions);
    }

    /**
     * GET - Transactions à exécuter avant une date donnée
     */
    @GetMapping("/before")
    public ResponseEntity<List<ModelTransactionProgrammee>> getTransactionsBeforeDate(@RequestParam Timestamp date) {
        List<ModelTransactionProgrammee> transactions = transactionProgrammeeService.getTransactionsBeforeDate(date);
        return ResponseEntity.ok(transactions);
    }

}
