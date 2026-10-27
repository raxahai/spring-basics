package org.example.repository;

import org.example.entity.Transaction;

import java.util.List;

public interface PaymentRepository {
    List<Transaction> findAll();
}
