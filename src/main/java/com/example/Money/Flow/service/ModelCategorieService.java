package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelCategorie;
import com.example.Money.Flow.repository.ModelCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelCategorieService {

    @Autowired
    private ModelCategorieRepository categorieRepository;

    /**
     * Récupérer toutes les catégories
     */
    public List<ModelCategorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    /**
     * Récupérer une catégorie par ID
     */
    public Optional<ModelCategorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    /**
     * Créer une nouvelle catégorie
     */
    public ModelCategorie createCategorie(ModelCategorie categorie) {
        // Tu peux ici vérifier si le libellé existe déjà si tu veux l'unicité
        return categorieRepository.save(categorie);
    }

    /**
     * Mettre à jour une catégorie existante
     */
    public ModelCategorie updateCategorie(Long id, ModelCategorie updatedCategorie) {
        return categorieRepository.findById(id)
                .map(categorie -> {
                    categorie.setLibelle(updatedCategorie.getLibelle());
                    categorie.setCouleur(updatedCategorie.getCouleur());
                    categorie.setIcone(updatedCategorie.getIcone());
                    return categorieRepository.save(categorie);
                })
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
    }

    /**
     * Supprimer une catégorie par ID
     */
    public void deleteCategorie(Long id) {
        if (!categorieRepository.existsById(id)) {
            throw new RuntimeException("Catégorie introuvable");
        }
        categorieRepository.deleteById(id);
    }

    /**
     * Récupérer une catégorie par libellé
     */
    public Optional<ModelCategorie> getCategorieByLibelle(String libelle) {
        return categorieRepository.findByLibelle(libelle);
    }
}
