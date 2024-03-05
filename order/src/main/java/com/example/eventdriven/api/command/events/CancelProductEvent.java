package com.example.eventdriven.api.command.events;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelProductEvent {
    String productId;
    String status;
}
