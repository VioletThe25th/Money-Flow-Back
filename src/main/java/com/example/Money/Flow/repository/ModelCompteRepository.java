package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelCompteRepository extends JpaRepository<ModelCompte, Long> {

    List<ModelCompte> findByOwnerId(Long ownerId);

    List<ModelCompte> findByLibelleContainingIgnoreCase(String libelle);
}
