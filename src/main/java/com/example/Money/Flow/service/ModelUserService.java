package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelRole;
import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.repository.ModelUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ModelUserService {

    @Autowired
    private ModelUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Enregistre un nouvel utilisateur
     */
    public ModelUser createUser(ModelUser user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail_confirme(false);
        return userRepository.save(user);
    }

    /**
     * Liste tous les utilisateurs
     */
    public List<ModelUser> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Récupère un utilisateur par ID
     */
    public Optional<ModelUser> getUserById(BigInteger id) {
        return userRepository.findById(id);
    }

    /**
     * Met à jour un utilisateur existant
     */
    public ModelUser updateUser(BigInteger id, ModelUser updatedUser) {
        return userRepository.findById(id).map(user -> {

            user.setNom(updatedUser.getNom());
            user.setPrenom(updatedUser.getPrenom());
            user.setEmail(updatedUser.getEmail());

            // Vérifie si le mot de passe a changé
            if (!updatedUser.getPassword().equals(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            user.setEmail_confirme(updatedUser.getEmail_confirme());
            user.setRole(updatedUser.getRole());

            return userRepository.save(user);

        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    /**
     * Supprime un utilisateur par ID
     */
    public void deleteUser(BigInteger id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé");
        }

        userRepository.deleteById(id);
    }

    public boolean authenticateUser(String email, String rawPassword) {
        Optional<ModelUser> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return false;
        }

        ModelUser user = userOpt.get();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
