package com.example.eventdriven.api.command.handler;

import com.example.eventdriven.api.command.events.ProductCompleteEvent;
import com.example.eventdriven.domain.Order;
import com.example.eventdriven.api.command.events.ProductCreatedEvent;
import com.example.eventdriven.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
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
        orderRepo.save(order);
    }

    @EventHandler
    public void on(ProductCompleteEvent event) {
         System.out.println("sending email");
    }
}