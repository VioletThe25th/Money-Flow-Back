package com.example.Money.Flow.service;

import com.example.Money.Flow.Model.ModelUser;
import com.example.Money.Flow.repository.ModelUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ModelUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ModelUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        // On utilise l'email comme identifiant et on peut définir les autorités en fonction du rôle
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER") // Adaptez en fonction de votre entité ModelRole
                .build();
    }
}
