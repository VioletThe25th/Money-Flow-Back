package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelCategorieRepository extends JpaRepository<ModelCategorie, Long> {

    Optional<ModelCategorie> findByLibelle(String libelle);
}
