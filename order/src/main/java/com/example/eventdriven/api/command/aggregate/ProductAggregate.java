package com.example.eventdriven.api.command.aggregate;


import com.example.eventdriven.api.command.command.CompleteProductCommand;
import com.example.eventdriven.api.command.command.CreateProductCommand;
import com.example.eventdriven.api.command.events.ProductCompleteEvent;
import com.example.eventdriven.api.command.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
 import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @AggregateIdentifier
    String productId;
    String name;
    int count;

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        if(command.getCount() <=0)
            throw new IllegalArgumentException("Count must be greater than 0");

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(command,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);

    }


    @EventSourcingHandler
    public void  on(ProductCreatedEvent event) {
        this.productId = event.getProductId();
        this.name = event.getName();
        this.count = event.getCount();
    }


    @CommandHandler
    public ProductAggregate(CompleteProductCommand command) {
        ProductCompleteEvent productCompleteEvent = new ProductCompleteEvent();
        productCompleteEvent.setProductId(command.getProductId());
        AggregateLifecycle.apply(productCompleteEvent);
    }

    @EventSourcingHandler
    public void  on(ProductCompleteEvent event) {
        this.productId = event.getProductId();
    }

}
