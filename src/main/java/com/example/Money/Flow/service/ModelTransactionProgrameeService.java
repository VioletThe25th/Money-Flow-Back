package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelCompte;
import com.example.Money.Flow.Model.ModelTransactionProgrammee;
import com.example.Money.Flow.repository.ModelCompteRepository;
import com.example.Money.Flow.repository.ModelTransactionProgrammeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ModelTransactionProgrammeeService {

    @Autowired
    private ModelTransactionProgrammeeRepository transactionProgrammeeRepository;

    @Autowired
    private ModelCompteRepository compteRepository;

    /**
     * Lister toutes les transactions programmées
     */
    public List<ModelTransactionProgrammee> getAllTransactionsProgrammee() {
        return transactionProgrammeeRepository.findAll();
    }

    /**
     * Récupérer une transaction programmée par ID
     */
    public Optional<ModelTransactionProgrammee> getTransactionProgrammeeById(Long id) {
        return transactionProgrammeeRepository.findById(id);
    }

    /**
     * Créer une transaction programmée
     */
    public ModelTransactionProgrammee createTransactionProgrammee(ModelTransactionProgrammee transactionProgrammee) {

        // Vérifier si les comptes existent
        ModelCompte compteDepart = compteRepository.findById(transactionProgrammee.getCompteDepart().getId())
                .orElseThrow(() -> new RuntimeException("Compte départ introuvable"));

        ModelCompte compteDestination = compteRepository.findById(transactionProgrammee.getCompteDestination().getId())
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable"));

        // Logique additionnelle si besoin

        return transactionProgrammeeRepository.save(transactionProgrammee);
    }

    /**
     * Mettre à jour une transaction programmée
     */
    public ModelTransactionProgrammee updateTransactionProgrammee(Long id, ModelTransactionProgrammee updatedTransactionProgrammee) {
        return transactionProgrammeeRepository.findById(id)
                .map(transaction -> {
                    transaction.setOwner(updatedTransactionProgrammee.getOwner());
                    transaction.setCompteDepart(updatedTransactionProgrammee.getCompteDepart());
                    transaction.setCompteDestination(updatedTransactionProgrammee.getCompteDestination());
                    transaction.setMontant(updatedTransactionProgrammee.getMontant());
                    transaction.setFrequence(updatedTransactionProgrammee.getFrequence());
                    transaction.setDateTransaction(updatedTransactionProgrammee.getDateTransaction());
                    return transactionProgrammeeRepository.save(transaction);
                })
                .orElseThrow(() -> new RuntimeException("Transaction programmée introuvable"));
    }

    /**
     * Supprimer une transaction programmée
     */
    public void deleteTransactionProgrammee(Long id) {
        if (!transactionProgrammeeRepository.existsById(id)) {
            throw new RuntimeException("Transaction programmée introuvable");
        }
        transactionProgrammeeRepository.deleteById(id);
    }

    /**
     * Récupérer les transactions programmées par owner
     */
    public List<ModelTransactionProgrammee> getTransactionsByOwnerId(Long ownerId) {
        return transactionProgrammeeRepository.findByOwnerId(ownerId);
    }

    /**
     * Récupérer les transactions à exécuter avant une date donnée
     */
    public List<ModelTransactionProgrammee> getTransactionsBeforeDate(Timestamp date) {
        return transactionProgrammeeRepository.findByDateTransactionBefore(date);
    }

}
