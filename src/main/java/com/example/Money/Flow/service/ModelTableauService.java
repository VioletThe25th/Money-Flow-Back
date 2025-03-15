package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelTableau;
import com.example.Money.Flow.repository.ModelTableauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ModelTableauService {

    @Autowired
    private ModelTableauRepository tableauRepository;

    /**
     * Lister tous les tableaux
     */
    public List<ModelTableau> getAllTableaux() {
        return tableauRepository.findAll();
    }

    /**
     * Récupérer un tableau par son ID
     */
    public Optional<ModelTableau> getTableauById(Long id) {
        return tableauRepository.findById(id);
    }

    /**
     * Créer un tableau
     */
    public ModelTableau createTableau(ModelTableau tableau) {
        if (tableauRepository.findByOwnerId(tableau.getOwner().getId()).isPresent()) {
            throw new RuntimeException("Cet utilisateur possède déjà un tableau !");
        }
        return tableauRepository.save(tableau);
    }

    /**
     * Mettre à jour un tableau
     */
    public ModelTableau updateTableau(Long id, ModelTableau updatedTableau) {
        return tableauRepository.findById(id)
                .map(tableau -> {
                    tableau.setOwner(updatedTableau.getOwner());
                    return tableauRepository.save(tableau);
                })
                .orElseThrow(() -> new RuntimeException("Tableau introuvable"));
    }

    /**
     * Supprimer un tableau
     */
    public void deleteTableau(Long id) {
        if (!tableauRepository.existsById(id)) {
            throw new RuntimeException("Tableau introuvable");
        }
        tableauRepository.deleteById(id);
    }

    /**
     * Récupérer un tableau par l'ID de l'owner
     */
    public Optional<ModelTableau> getTableauByOwnerId(BigInteger ownerId) {
        return tableauRepository.findByOwnerId(ownerId);
    }
}
