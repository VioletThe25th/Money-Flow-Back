package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelClassification;
import com.example.Money.Flow.repository.ModelClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelClassificationService {

    @Autowired
    private ModelClassificationRepository classificationRepository;

    /**
     * Lister toutes les classifications
     */
    public List<ModelClassification> getAllClassifications() {
        return classificationRepository.findAll();
    }

    /**
     * Récupérer une classification par son ID
     */
    public Optional<ModelClassification> getClassificationById(Long id) {
        return classificationRepository.findById(id);
    }

    /**
     * Créer une nouvelle classification
     */
    public ModelClassification createClassification(ModelClassification classification) {
        return classificationRepository.save(classification);
    }

    /**
     * Mettre à jour une classification
     */
    public ModelClassification updateClassification(Long id, ModelClassification updatedClassification) {
        return classificationRepository.findById(id)
                .map(classification -> {
                    classification.setTransaction(updatedClassification.getTransaction());
                    classification.setCategorie(updatedClassification.getCategorie());
                    return classificationRepository.save(classification);
                })
                .orElseThrow(() -> new RuntimeException("Classification introuvable"));
    }

    /**
     * Supprimer une classification par son ID
     */
    public void deleteClassification(Long id) {
        if (!classificationRepository.existsById(id)) {
            throw new RuntimeException("Classification introuvable");
        }

        classificationRepository.deleteById(id);
    }

    /**
     * Lister les classifications d'une transaction spécifique
     */
    public List<ModelClassification> getClassificationsByTransactionId(Long transactionId) {
        return classificationRepository.findByTransactionId(transactionId);
    }

    /**
     * Lister les classifications d'une catégorie spécifique
     */
    public List<ModelClassification> getClassificationsByCategorieId(Long categorieId) {
        return classificationRepository.findByCategorieId(categorieId);
    }
}
