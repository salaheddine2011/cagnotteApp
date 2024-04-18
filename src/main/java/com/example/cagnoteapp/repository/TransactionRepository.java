package com.example.cagnoteapp.repository;

import com.example.cagnoteapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByClientId(Long clientId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.client.id = :clientId")
    BigDecimal sumAmountByClientId(Long clientId);


}