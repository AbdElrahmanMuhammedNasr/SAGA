package com.example.eventdriven.api.command.events;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreatedEvent {
    String productId;
    String name;
    int count;
}
