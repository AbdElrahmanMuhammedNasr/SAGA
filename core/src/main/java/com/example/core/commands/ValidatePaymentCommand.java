package com.example.core.commands;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidatePaymentCommand implements Serializable {
    @TargetAggregateIdentifier
    String paymentId;
    String productId;
    String userId;

}
