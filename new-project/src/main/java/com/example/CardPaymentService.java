package com.example;

import org.springframework.stereotype.Component;

@Component("cardPayment")
public class CardPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Card Payment of  " + amount + " EUR was successfully processed!");
    }
}