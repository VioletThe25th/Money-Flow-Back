package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelTransactionRepository extends JpaRepository<ModelTransaction, Long> {

    List<ModelTransaction> findByOwnerId(Long ownerId);

    List<ModelTransaction> findByCompteDepartId(Long compteId);

    List<ModelTransaction> findByCompteDestinationId(Long compteId);

    List<ModelTransaction> findByClassificationId(Long classificationId);
}
