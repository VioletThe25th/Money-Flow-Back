package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelTableau;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ModelTableauRepository extends JpaRepository<ModelTableau, Long> {

    Optional<ModelTableau> findByOwnerId(Long ownerId);
}
