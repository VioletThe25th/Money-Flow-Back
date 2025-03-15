package com.example.Money.Flow.controller;

import com.example.Money.Flow.Model.ModelTransactionProgrammee;
import com.example.Money.Flow.service.ModelTransactionProgrammeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp; // Vérifiez si c'est bien ce type ou utilisez java.sql.Timestamp
import java.util.List;

@RestController
@RequestMapping("/api/transactions-programmees")
@Tag(name = "Transaction Programmée API", description = "Endpoints pour la gestion des transactions programmées")
public class TransactionProgrammeeController {

    @Autowired
    private ModelTransactionProgrammeeService transactionProgrammeeService;

    @Operation(summary = "Lister toutes les transactions programmées",
            description = "Retourne la liste de toutes les transactions programmées.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelTransactionProgrammee.class)))
    })
    @GetMapping
    public ResponseEntity<List<ModelTransactionProgrammee>> getAllTransactionsProgrammee() {
        List<ModelTransactionProgrammee> transactions = transactionProgrammeeService.getAllTransactionsProgrammee();
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Récupérer une transaction programmée par ID",
            description = "Retourne la transaction programmée correspondant à l'ID fourni.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelTransactionProgrammee.class))),
            @ApiResponse(responseCode = "404", description = "Transaction non trouvée", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ModelTransactionProgrammee> getTransactionProgrammeeById(@PathVariable Long id) {
        return transactionProgrammeeService.getTransactionProgrammeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer une transaction programmée",
            description = "Crée une nouvelle transaction programmée avec les informations fournies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction créée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelTransactionProgrammee.class))),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ModelTransactionProgrammee> createTransactionProgrammee(
            @RequestBody ModelTransactionProgrammee transaction) {
        try {
            ModelTransactionProgrammee created = transactionProgrammeeService.createTransactionProgrammee(transaction);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Mettre à jour une transaction programmée",
            description = "Met à jour la transaction programmée identifiée par l'ID avec les nouvelles informations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction mise à jour avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelTransactionProgrammee.class))),
            @ApiResponse(responseCode = "404", description = "Transaction non trouvée", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ModelTransactionProgrammee> updateTransactionProgrammee(
            @PathVariable Long id,
            @RequestBody ModelTransactionProgrammee transaction) {
        try {
            ModelTransactionProgrammee updated = transactionProgrammeeService.updateTransactionProgrammee(id, transaction);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une transaction programmée",
            description = "Supprime la transaction programmée identifiée par l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaction supprimée avec succès", content = @Content),
            @ApiResponse(responseCode = "404", description = "Transaction non trouvée", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionProgrammee(@PathVariable Long id) {
        try {
            transactionProgrammeeService.deleteTransactionProgrammee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lister les transactions programmées par owner",
            description = "Retourne la liste des transactions programmées associées à un owner donné.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelTransactionProgrammee.class)))
    })
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ModelTransactionProgrammee>> getTransactionsByOwnerId(@PathVariable Long ownerId) {
        List<ModelTransactionProgrammee> transactions = transactionProgrammeeService.getTransactionsByOwnerId(ownerId);
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Lister les transactions programmées avant une date donnée",
            description = "Retourne la liste des transactions programmées dont la date d'exécution est antérieure à la date fournie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ModelTransactionProgrammee.class)))
    })
    @GetMapping("/before")
    public ResponseEntity<List<ModelTransactionProgrammee>> getTransactionsBeforeDate(
            @Parameter(description = "Date limite pour l'exécution des transactions", required = true)
            @RequestParam("date") Timestamp date) {
        List<ModelTransactionProgrammee> transactions = transactionProgrammeeService.getTransactionsBeforeDate(date);
        return ResponseEntity.ok(transactions);
    }
}
