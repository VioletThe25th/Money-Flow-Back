package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelTransactionProgrammee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.List;

@Repository
public interface ModelTransactionProgrammeeRepository extends JpaRepository<ModelTransactionProgrammee, Long> {

    List<ModelTransactionProgrammee> findByOwnerId(Long ownerId);

    List<ModelTransactionProgrammee> findByCompteDepartId(Long compteDepartId);

    List<ModelTransactionProgrammee> findByCompteDestinationId(Long compteDestinationId);

    // Optionnel : trouver les transactions à exécuter avant une date donnée
    List<ModelTransactionProgrammee> findByDateTransactionBefore(Timestamp date);
}
