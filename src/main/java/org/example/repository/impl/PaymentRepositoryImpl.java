package org.example.repository.impl;

import org.example.entity.Transaction;
import org.example.repository.PaymentRepository;

import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

    public PaymentRepositoryImpl(){
        System.out.println("Payment Repository Created...");
    }

    @Override
    public List<Transaction> findAll() {
        return List.of();
    }
}
