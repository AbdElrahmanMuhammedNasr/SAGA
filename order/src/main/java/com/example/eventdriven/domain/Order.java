package com.example.eventdriven.domain;

import com.example.eventdriven.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_product")
public class Order   extends BaseEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    String productId;

    @Column(name = "name")
    String name;

    @Column(name = "count")
    int count;

    String status;
}
