package com.example.payment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment   implements Serializable {

    public static final long serialVersionUID = 2L;

    @Id
    @Column(name = "id")
    String paymentId;

    String orderId;
    String userId;

    Instant date= Instant.now();
}
