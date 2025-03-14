package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelCompte;
import com.example.Money.Flow.Model.ModelTransaction;
import com.example.Money.Flow.repository.ModelCompteRepository;
import com.example.Money.Flow.repository.ModelTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ModelTransactionService {

    @Autowired
    private ModelTransactionRepository transactionRepository;

    @Autowired
    private ModelCompteRepository compteRepository;

    /**
     * Lister toutes les transactions
     */
    public List<ModelTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Récupérer une transaction par ID
     */
    public Optional<ModelTransaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    /**
     * Créer une transaction
     */
    public ModelTransaction createTransaction(ModelTransaction transaction) {
        // Vérifier si le compte départ a assez de solde
        ModelCompte compteDepart = compteRepository.findById(transaction.getCompte_depart().getId())
                .orElseThrow(() -> new RuntimeException("Compte départ introuvable"));

        ModelCompte compteDestination = compteRepository.findById(transaction.getCompte_destination().getId())
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable"));

        BigDecimal montant = transaction.getMontant();

        if (compteDepart.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant sur le compte de départ");
        }

        // Débiter le compte départ
        compteDepart.setSolde(compteDepart.getSolde().subtract(montant));

        // Créditer le compte destination
        compteDestination.setSolde(compteDestination.getSolde().add(montant));

        // Sauvegarder les comptes
        compteRepository.save(compteDepart);
        compteRepository.save(compteDestination);

        // Sauvegarder la transaction
        return transactionRepository.save(transaction);
    }

    /**
     * Mettre à jour une transaction (rare, mais dispo si besoin)
     */
    public ModelTransaction updateTransaction(Long id, ModelTransaction updatedTransaction) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setLibelle(updatedTransaction.getLibelle());
                    transaction.setMontant(updatedTransaction.getMontant());
                    transaction.setCompte_depart(updatedTransaction.getCompte_depart());
                    transaction.setCompte_destination(updatedTransaction.getCompte_destination());
                    transaction.setClassification(updatedTransaction.getClassification());
                    transaction.setDate(updatedTransaction.getDate());
                    return transactionRepository.save(transaction);
                })
                .orElseThrow(() -> new RuntimeException("Transaction introuvable"));
    }

    /**
     * Supprimer une transaction (à utiliser avec précaution !)
     */
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction introuvable");
        }

        // Ici, on pourrait gérer le rollback sur les comptes si besoin

        transactionRepository.deleteById(id);
    }

    /**
     * Récupérer les transactions par owner
     */
    public List<ModelTransaction> getTransactionsByOwnerId(Long ownerId) {
        return transactionRepository.findByOwnerId(ownerId);
    }

    /**
     * Récupérer les transactions par compte départ
     */
    public List<ModelTransaction> getTransactionsByCompteDepartId(Long compteId) {
        return transactionRepository.findByCompteDepartId(compteId);
    }

    /**
     * Récupérer les transactions par compte destination
     */
    public List<ModelTransaction> getTransactionsByCompteDestinationId(Long compteId) {
        return transactionRepository.findByCompteDestinationId(compteId);
    }

    /**
     * Récupérer les transactions par classification
     */
    public List<ModelTransaction> getTransactionsByClassificationId(Long classificationId) {
        return transactionRepository.findByClassificationId(classificationId);
    }
}
