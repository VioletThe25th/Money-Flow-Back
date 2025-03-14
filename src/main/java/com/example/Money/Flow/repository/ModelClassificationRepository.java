package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelClassificationRepository extends JpaRepository<ModelClassification, Long> {

    List<ModelClassification> findByTransactionId(Long transactionId);

    List<ModelClassification> findByCategorieId(Long categorieId);
}
