package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelRole;
import com.example.Money.Flow.repository.ModelRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelRoleService {

    @Autowired
    private ModelRoleRepository roleRepository;

    /**
     * Lister tous les rôles
     */
    public List<ModelRole> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Récupérer un rôle par son ID
     */
    public Optional<ModelRole> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    /**
     * Créer un nouveau rôle
     */
    public ModelRole createRole(ModelRole role) {
        // Si tu veux empêcher les doublons de rôle
        if (roleRepository.findByRole(role.getRole()).isPresent()) {
            throw new RuntimeException("Ce rôle existe déjà !");
        }
        return roleRepository.save(role);
    }

    /**
     * Mettre à jour un rôle existant
     */
    public ModelRole updateRole(Long id, ModelRole updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setRole(updatedRole.getRole());
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Rôle introuvable"));
    }

    /**
     * Supprimer un rôle par ID
     */
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Rôle introuvable");
        }
        roleRepository.deleteById(id);
    }

    /**
     * Récupérer un rôle par son nom
     */
    public Optional<ModelRole> getRoleByName(String roleName) {
        return roleRepository.findByRole(roleName);
    }
}
