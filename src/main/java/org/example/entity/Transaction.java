package org.example.entity;

import org.example.enums.TransactionType;

import java.sql.Timestamp;

public class Transaction {
    private Long id;
    private TransactionType type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
