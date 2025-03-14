package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelBudgetPartage;
import com.example.Money.Flow.repository.ModelBudgetPartageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelBudgetPartageService {

    @Autowired
    private ModelBudgetPartageRepository budgetPartageRepository;

    /**
     * Lister tous les budgets partagés
     */
    public List<ModelBudgetPartage> getAllBudgetPartages() {
        return budgetPartageRepository.findAll();
    }

    /**
     * Récupérer un budget partagé par son ID
     */
    public Optional<ModelBudgetPartage> getBudgetPartageById(Long id) {
        return budgetPartageRepository.findById(id);
    }

    /**
     * Créer un budget partagé
     */
    public ModelBudgetPartage createBudgetPartage(ModelBudgetPartage budgetPartage) {
        return budgetPartageRepository.save(budgetPartage);
    }

    /**
     * Mettre à jour un budget partagé existant
     */
    public ModelBudgetPartage updateBudgetPartage(Long id, ModelBudgetPartage updatedBudgetPartage) {
        return budgetPartageRepository.findById(id)
                .map(budgetPartage -> {
                    budgetPartage.setUser_id(updatedBudgetPartage.getUser_id());
                    budgetPartage.setCompte(updatedBudgetPartage.getCompte());
                    budgetPartage.setRole(updatedBudgetPartage.getRole());
                    return budgetPartageRepository.save(budgetPartage);
                })
                .orElseThrow(() -> new RuntimeException("Budget partagé introuvable"));
    }

    /**
     * Supprimer un budget partagé
     */
    public void deleteBudgetPartage(Long id) {
        if (!budgetPartageRepository.existsById(id)) {
            throw new RuntimeException("Budget partagé introuvable");
        }

        budgetPartageRepository.deleteById(id);
    }

    /**
     * (Optionnel) Trouver les budgets partagés pour un utilisateur donné
     */
    public List<ModelBudgetPartage> getBudgetPartagesByUserId(Long userId) {
        return budgetPartageRepository.findByUserId(userId);
    }

    /**
     * (Optionnel) Trouver les budgets partagés pour un compte donné
     */
    public List<ModelBudgetPartage> getBudgetPartagesByCompteId(Long compteId) {
        return budgetPartageRepository.findByCompteId(compteId);
    }
}
