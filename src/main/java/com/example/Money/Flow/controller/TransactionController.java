package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelTransaction;
import com.example.Money.Flow.service.ModelTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private ModelTransactionService transactionService;

    /**
     * GET - Liste toutes les transactions
     */
    @GetMapping
    public ResponseEntity<List<ModelTransaction>> getAllTransactions() {
        List<ModelTransaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    /**
     * GET - Récupérer une transaction par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelTransaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST - Créer une transaction
     */
    @PostMapping
    public ResponseEntity<ModelTransaction> createTransaction(@RequestBody ModelTransaction transaction) {
        try {
            ModelTransaction created = transactionService.createTransaction(transaction);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT - Mettre à jour une transaction
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelTransaction> updateTransaction(@PathVariable Long id, @RequestBody ModelTransaction transaction) {
        try {
            ModelTransaction updated = transactionService.updateTransaction(id, transaction);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - Supprimer une transaction
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET - Transactions par owner
     */
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByOwnerId(@PathVariable Long ownerId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByOwnerId(ownerId);
        return ResponseEntity.ok(transactions);
    }

    /**
     * GET - Transactions par compte départ
     */
    @GetMapping("/compte-depart/{compteId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByCompteDepartId(@PathVariable Long compteId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByCompteDepartId(compteId);
        return ResponseEntity.ok(transactions);
    }

    /**
     * GET - Transactions par compte destination
     */
    @GetMapping("/compte-destination/{compteId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByCompteDestinationId(@PathVariable Long compteId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByCompteDestinationId(compteId);
        return ResponseEntity.ok(transactions);
    }

    /**
     * GET - Transactions par classification
     */
    @GetMapping("/classification/{classificationId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByClassificationId(@PathVariable Long classificationId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByClassificationId(classificationId);
        return ResponseEntity.ok(transactions);
    }
}
