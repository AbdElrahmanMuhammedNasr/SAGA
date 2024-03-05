package com.example.eventdriven.api.command.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class CancelProductCommand {
    @TargetAggregateIdentifier
    String productId;
    String status = "CANCELLED";

}
