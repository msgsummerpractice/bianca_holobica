package com.example;

import org.springframework.stereotype.Component;

@Component("cashPayment")
public class CashPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Cash payment of " + amount + " EUR was successfully processed!");
    }
}