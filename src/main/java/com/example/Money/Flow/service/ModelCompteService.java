package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelCompte;
import com.example.Money.Flow.repository.ModelCompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelCompteService {

    @Autowired
    private ModelCompteRepository compteRepository;

    /**
     * Lister tous les comptes
     */
    public List<ModelCompte> getAllComptes() {
        return compteRepository.findAll();
    }

    /**
     * Récupérer un compte par ID
     */
    public Optional<ModelCompte> getCompteById(Long id) {
        return compteRepository.findById(id);
    }

    /**
     * Créer un nouveau compte
     */
    public ModelCompte createCompte(ModelCompte compte) {
        // Tu peux ajouter ici de la logique (par exemple, vérifier le solde >= 0)
        return compteRepository.save(compte);
    }

    /**
     * Mettre à jour un compte existant
     */
    public ModelCompte updateCompte(Long id, ModelCompte updatedCompte) {
        return compteRepository.findById(id)
                .map(compte -> {
                    compte.setLibelle(updatedCompte.getLibelle());
                    compte.setSolde(updatedCompte.getSolde());
                    compte.setAlerte_min(updatedCompte.getAlerte_min());
                    compte.setAlerte_max(updatedCompte.getAlerte_max());
                    compte.setType(updatedCompte.getType());
                    // Si on veut changer le owner (en général non, mais dispo si besoin)
                    compte.setOwner(updatedCompte.getOwner());
                    return compteRepository.save(compte);
                })
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
    }

    /**
     * Supprimer un compte par ID
     */
    public void deleteCompte(Long id) {
        if (!compteRepository.existsById(id)) {
            throw new RuntimeException("Compte introuvable");
        }
        compteRepository.deleteById(id);
    }

    /**
     * Liste des comptes d'un utilisateur
     */
    public List<ModelCompte> getComptesByOwnerId(Long ownerId) {
        return compteRepository.findByOwnerId(ownerId);
    }

    /**
     * Rechercher un compte par libellé
     */
    public List<ModelCompte> searchByLibelle(String libelle) {
        return compteRepository.findByLibelleContainingIgnoreCase(libelle);
    }
}
