package com.example.eventdriven.api.command.aggregate;


import com.example.eventdriven.api.command.command.CancelProductCommand;
import com.example.eventdriven.api.command.command.CompleteProductCommand;
import com.example.eventdriven.api.command.command.CreateProductCommand;
import com.example.core.events.CancelProductEvent;
import com.example.eventdriven.api.command.events.ProductCompleteEvent;
import com.example.eventdriven.api.command.events.ProductCreatedEvent;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
 import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAggregate {
    protected ProductAggregate() {

    }
    @AggregateIdentifier
    String productId;
    String name;
    int count;
    String status;

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        if(command.getCount() <=0)
            throw new IllegalArgumentException("Count must be greater than 0");

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(command,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);

    }


    @EventSourcingHandler(payloadType = ProductCreatedEvent.class)
    public void  on(ProductCreatedEvent event) {
        this.productId = event.getProductId();
        this.name = event.getName();
        this.count = event.getCount();
    }


    @CommandHandler(payloadType = CompleteProductCommand.class)
    public void handleCompleteProductCommand(CompleteProductCommand command) {
        ProductCompleteEvent productCompleteEvent = new ProductCompleteEvent();
        productCompleteEvent.setProductId(command.getProductId());
        AggregateLifecycle.apply(productCompleteEvent);
    }

    @EventSourcingHandler
    public void  onHandleCompleteProductCommand(ProductCompleteEvent event) {
        this.productId = event.getProductId();
    }


    @CommandHandler
    public void handleCancelProductCommand(CancelProductCommand command){
        CancelProductEvent cancelEvent = CancelProductEvent.builder().status(command.getStatus()).productId(command.getProductId()).build();
        AggregateLifecycle.apply(cancelEvent);

    }

    @EventSourcingHandler(payloadType = CancelProductEvent.class)
    public void  onHandleCancelProductCommand(CancelProductEvent event) {
        this.productId  = event.getProductId();
        this.status = event.getStatus();
    }

}
