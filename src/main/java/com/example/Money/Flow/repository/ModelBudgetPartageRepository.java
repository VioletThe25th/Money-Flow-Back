package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelBudgetPartage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelBudgetPartageRepository extends JpaRepository<ModelBudgetPartage, Long> {

    List<ModelBudgetPartage> findByUserId(Long userId);
    List<ModelBudgetPartage> findByCompteId(Long compteId);
}
