package com.example.loan.repository;

import com.example.loan.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    Optional<Entry> findByApplicationId(Long applicationId);
}
