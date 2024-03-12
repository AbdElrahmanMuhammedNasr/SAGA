package com.example.core.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CancelPaymentCommand {
    @TargetAggregateIdentifier
    String paymentId;
    String productId;
}
