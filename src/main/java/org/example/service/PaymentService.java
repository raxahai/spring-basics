package org.example.service;

import org.example.repository.PaymentRepository;

public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService() {
        System.out.println("Payment Service Created...");
    }

    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
