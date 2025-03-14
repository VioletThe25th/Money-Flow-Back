package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelWidget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelWidgetRepository extends JpaRepository<ModelWidget, Long> {

    Optional<ModelWidget> findByLibelle(String libelle);

    boolean existsByLibelle(String libelle);
}
