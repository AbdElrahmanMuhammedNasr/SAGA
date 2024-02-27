package com.example.eventdriven.repo;

import com.example.eventdriven.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
    Order findByName(String name);


}
