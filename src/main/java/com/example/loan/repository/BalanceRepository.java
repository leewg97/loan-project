package com.example.loan.repository;

import com.example.loan.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByApplicationId(Long applicationId);
}
