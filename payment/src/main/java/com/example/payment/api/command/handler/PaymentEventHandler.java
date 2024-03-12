package com.example.payment.api.command.handler;

import com.example.core.events.PaymentCancelledEvent;
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
//        if(true)
//            throw new RuntimeException("Payment event handler");
        Payment payment = new Payment();
        payment.setPaymentId(event.getPaymentId());
        payment.setOrderId(event.getProductId());
        payment.setUserId(event.getUserId());
        payment.setDate(Instant.now());
        payment.setStatus("SUCCESS");
        paymentRepo.save(payment);
    }

    @EventHandler
    public void handle(PaymentCancelledEvent event) {
        paymentRepo.findById(event.getPaymentId())
                .ifPresent(pay-> pay.setStatus("CANCELLED"));
    }
}
