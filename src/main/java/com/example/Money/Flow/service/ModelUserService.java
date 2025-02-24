package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.repository.ModelUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ModelUserService {

    @Autowired
    private ModelUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ModelUser registerUser(ModelUser user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email déjà utilisé");
        }
        // Encodage du mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Par défaut, email non confirmé et rôle à définir (par exemple, un rôle par défaut)
        user.setEmail_confirme(false);
        // Vous pouvez définir ici un rôle par défaut si nécessaire
        return userRepository.save(user);
    }

    public Iterable<ModelUser> getAllUsers() {
        return userRepository.findAll();
    }
}
