package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelTransaction;
import com.example.Money.Flow.service.ModelTransactionService;
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
@RequestMapping("/api/transactions")
@Tag(name = "Transaction API", description = "Endpoints pour la gestion des transactions")
public class TransactionController {

    @Autowired
    private ModelTransactionService transactionService;

    @Operation(summary = "Lister toutes les transactions", description = "Retourne la liste de toutes les transactions enregistrées.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des transactions récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelTransaction>> getAllTransactions() {
        List<ModelTransaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Récupérer une transaction par ID", description = "Retourne la transaction correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction trouvée",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class))),
            @ApiResponse(responseCode = "404", description = "Transaction non trouvée", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelTransaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer une transaction", description = "Crée une nouvelle transaction avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction créée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelTransaction> createTransaction(@RequestBody ModelTransaction transaction) {
        try {
            ModelTransaction created = transactionService.createTransaction(transaction);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Mettre à jour une transaction", description = "Met à jour la transaction identifiée par l'ID avec les nouvelles informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction mise à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class))),
            @ApiResponse(responseCode = "404", description = "Transaction non trouvée", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelTransaction> updateTransaction(@PathVariable Long id, @RequestBody ModelTransaction transaction) {
        try {
            ModelTransaction updated = transactionService.updateTransaction(id, transaction);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une transaction", description = "Supprime la transaction identifiée par l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaction supprimée avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Transaction non trouvée", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lister les transactions par owner", description = "Retourne la liste des transactions associées à un owner donné.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des transactions récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class)))
    })
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByOwnerId(@PathVariable Long ownerId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByOwnerId(ownerId);
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Lister les transactions par compte départ", description = "Retourne la liste des transactions dont le compte de départ correspond à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des transactions récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class)))
    })
    @GetMapping("/compte-depart/{compteId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByCompteDepartId(@PathVariable Long compteId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByCompteDepartId(compteId);
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Lister les transactions par compte destination", description = "Retourne la liste des transactions dont le compte destination correspond à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des transactions récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class)))
    })
    @GetMapping("/compte-destination/{compteId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByCompteDestinationId(@PathVariable Long compteId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByCompteDestinationId(compteId);
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Lister les transactions par classification", description = "Retourne la liste des transactions associées à une classification donnée.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des transactions récupérée avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ModelTransaction.class)))
    })
    @GetMapping("/classification/{classificationId}")
    public ResponseEntity<List<ModelTransaction>> getTransactionsByClassificationId(@PathVariable Long classificationId) {
        List<ModelTransaction> transactions = transactionService.getTransactionsByClassificationId(classificationId);
        return ResponseEntity.ok(transactions);
    }
}
