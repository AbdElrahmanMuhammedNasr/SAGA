package com.example.eventdriven.saga;


 import com.example.core.commands.CancelPaymentCommand;
 import com.example.core.commands.ValidatePaymentCommand;
 import com.example.core.domain.ACUSer;
 import com.example.core.events.CancelProductEvent;
 import com.example.core.events.PaymentCancelledEvent;
 import com.example.core.events.PaymentEvent;
 import com.example.core.querys.FindUserQuery;
import com.example.eventdriven.api.command.command.CancelProductCommand;
import com.example.eventdriven.api.command.command.CompleteProductCommand;
import com.example.eventdriven.api.command.events.ProductCompleteEvent;
import com.example.eventdriven.api.command.events.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

 import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderSaga {

    @Autowired private  transient   CommandGateway commandGateway;
    @Autowired private transient  QueryGateway queryGateway;
        public OrderSaga() {}


        @StartSaga
        @SagaEventHandler(associationProperty = "productId")
        public void handle(ProductCreatedEvent productCreatedEvent) {
                log.info("created product " + productCreatedEvent.getProductId());

            FindUserQuery query  = new FindUserQuery("user");
            ACUSer acuSer = queryGateway.query(query, ResponseTypes.instanceOf(ACUSer.class)).join();

            try {
                        ValidatePaymentCommand validatePaymentCommand
                                = ValidatePaymentCommand.builder()
                                .productId(productCreatedEvent.getProductId())
                                .paymentId(UUID.randomUUID().toString())
                                .userId(acuSer.getId())
                                .build();

                        commandGateway.sendAndWait(validatePaymentCommand);
                }catch (Exception e){
                log.error("something went wrong "+e.getMessage());
                        this.cancelProduct(productCreatedEvent.getProductId());
                }

        }

        private void cancelProduct(String productId){
            CancelProductCommand cancelProductCommand = CancelProductCommand.builder()
                    .productId(productId)
                    .build();
            commandGateway.send(cancelProductCommand);

        }
//        ////////////////////////////

        @SagaEventHandler(associationProperty = "productId")
        public void handle(PaymentEvent paymentEvent) {

               try {
                   if(true)
                        throw new Exception("something went wrong in sage paymenrt eent");

                   CompleteProductCommand completeProductCommand
                               = CompleteProductCommand.builder()
                               .productId(paymentEvent.getProductId())
                               .build();
                       commandGateway.sendAndWait(completeProductCommand);
               }catch (Exception e){
                        log.error("something went wrong in saga "+e.getMessage());
                   cancelPaymentCommand(paymentEvent);
               }
        }
    private void cancelPaymentCommand(PaymentEvent event) {
        CancelPaymentCommand cancelPaymentCommand
                = new CancelPaymentCommand(event.getPaymentId(), event.getProductId());

        commandGateway.sendAndWait(cancelPaymentCommand);
    }

        @SagaEventHandler(associationProperty = "productId")
        @EndSaga
        public void handle(ProductCompleteEvent event) {
                log.info("End cycle " + event.getProductId());
        }

    @SagaEventHandler(associationProperty = "productId")
    public void handle(PaymentCancelledEvent event) {
        log.info("PaymentCancelledEvent in Saga for " + event.getProductId());
        cancelProduct(event.getProductId());
    }

    @SagaEventHandler(associationProperty = "productId")
    @EndSaga
    public void handle(CancelProductEvent event) {
        log.info("OrderCancelledEvent in Saga for Order Id : {}",
                event.getProductId());
    }


}
