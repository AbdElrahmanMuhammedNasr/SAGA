package com.example.eventdriven.api.command.rest;

import com.example.eventdriven.domain.Order;
import com.example.eventdriven.api.command.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductResource {

    private final CommandGateway commandGateway;

  @PostMapping("/product")
  public String save(@RequestBody Order order) {

        CreateProductCommand productCommand = CreateProductCommand
                .builder()
                .name(order.getName())
                .count(order.getCount())
                .productId(UUID.randomUUID().toString())
                .build();

        String value = commandGateway.sendAndWait(productCommand);
        return value;

    }
}
