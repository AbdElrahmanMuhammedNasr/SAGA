package com.example.payment.api.command.handler;

import com.example.core.events.PaymentEvent;
import com.example.payment.domain.Payment;
import com.example.payment.repo.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PaymentEventHandler {

    private final PaymentRepo paymentRepo;

    @EventHandler
    public void handle(PaymentEvent event) {
        Payment payment = new Payment();
        payment.setPaymentId(event.getPaymentId());
        payment.setOrderId(event.getProductId());
        payment.setDate(Instant.now());
        paymentRepo.save(payment);
    }

}
