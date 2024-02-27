package com.example.eventdriven.saga;


import com.example.core.commands.ValidatePaymentCommand;
import com.example.core.events.PaymentEvent;
import com.example.eventdriven.api.command.command.CompleteProductCommand;
import com.example.eventdriven.api.command.events.ProductCompleteEvent;
import com.example.eventdriven.api.command.events.ProductCreatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderSaga {

        @Autowired private transient   CommandGateway commandGateway;

        public OrderSaga() {}

        @StartSaga
        @SagaEventHandler(associationProperty = "productId")
        public void handle(ProductCreatedEvent productCreatedEvent) {
                log.info("created product " + productCreatedEvent.getProductId());

                try {
                        ValidatePaymentCommand validatePaymentCommand
                                = ValidatePaymentCommand.builder()
                                .productId(productCreatedEvent.getProductId())
                                .paymentId(UUID.randomUUID().toString())
                                .userId("1236545")
                                .build();

                        commandGateway.sendAndWait(validatePaymentCommand);
                }catch (Exception e){

                }

        }

        @SagaEventHandler(associationProperty = "productId")
        public void handle(PaymentEvent paymentEvent) {

               try {
                       CompleteProductCommand completeProductCommand
                               = CompleteProductCommand.builder()
                               .productId(paymentEvent.getProductId())
                               .build();
                       commandGateway.sendAndWait(completeProductCommand);
               }catch (Exception e){

               }

        }

        @SagaEventHandler(associationProperty = "productId")
        @EndSaga
        public void handle(ProductCompleteEvent event) {
                log.info("End cycle " + event.getProductId());
        }

}
