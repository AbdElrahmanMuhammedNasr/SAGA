package com.example.payment.api.command.aggregate;

import com.example.core.commands.CancelPaymentCommand;
import com.example.core.commands.ValidatePaymentCommand;
import com.example.core.events.PaymentCancelledEvent;
import com.example.core.events.PaymentEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentAggregate {
   @AggregateIdentifier
   String paymentId;
    String productId;
    String userId;

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        this.productId = validatePaymentCommand.getProductId();
        this.userId = validatePaymentCommand.getUserId();
        PaymentEvent paymentEvent = new PaymentEvent();
        BeanUtils.copyProperties(validatePaymentCommand, paymentEvent);
        AggregateLifecycle.apply(paymentEvent);

    }

    @EventSourcingHandler
    public void on(PaymentEvent paymentEvent) {
        this.productId = paymentEvent.getProductId();
        this.userId = paymentEvent.getUserId();
        this.paymentId = paymentEvent.getPaymentId();

    }


    @CommandHandler
    public void handleCancelPaymentCommand(CancelPaymentCommand cancelPaymentCommand) {
        PaymentCancelledEvent paymentCancelledEvent = new PaymentCancelledEvent();
        paymentCancelledEvent.setPaymentId(cancelPaymentCommand.getPaymentId());
        AggregateLifecycle.apply(paymentCancelledEvent);
    }

    @EventSourcingHandler
    public void  onCancelPaymentCommand(PaymentCancelledEvent event) {
        this.productId = event.getProductId();
    }
}
