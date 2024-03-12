package com.example.eventdriven.api.command.handler;

import com.example.core.events.CancelProductEvent;
import com.example.eventdriven.api.command.events.ProductCompleteEvent;
import com.example.eventdriven.domain.Order;
import com.example.eventdriven.api.command.events.ProductCreatedEvent;
import com.example.eventdriven.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {
    private final OrderRepo orderRepo;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        Order order = new Order();
        order.setProductId(event.getProductId());
        order.setName(event.getName());
        order.setCount(event.getCount());
        order.setStatus("CREATED");
        orderRepo.save(order);
    }

    @EventHandler
    public void on(ProductCompleteEvent event) {
         System.out.println("sending email");
    }

    @EventHandler
    public void on(CancelProductEvent event) {
        System.out.println("cancle ----------------");

        orderRepo.findById(event.getProductId())
                .ifPresent(order -> {
                    order.setStatus(event.getStatus());
                    orderRepo.save(order);
                });
    }
}
