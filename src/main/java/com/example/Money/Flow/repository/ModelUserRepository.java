package com.example.Money.Flow.repository;

import com.example.Money.Flow.Model.ModelUser;
import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelUserRepository extends JpaRepository<ModelUser, BigInteger> {
    Optional<ModelUser> findByEmail(String email);
}
