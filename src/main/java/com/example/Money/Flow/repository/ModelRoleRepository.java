package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRoleRepository extends JpaRepository<ModelRole, Long> {

    Optional<ModelRole> findByRole(String role);
}
