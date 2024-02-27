package com.example.eventdriven.api.query.handler;

import com.example.eventdriven.domain.Order;
import com.example.eventdriven.repo.OrderRepo;
import com.example.eventdriven.api.query.querys.FindOneOrderQuery;
import com.example.eventdriven.api.query.querys.FindOrdersQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final OrderRepo orderRepo;

    @QueryHandler
    public List<Order> findOrders(FindOrdersQuery findOrdersQuery){
        return orderRepo.findAll();
    }

    @QueryHandler
    public Order findOneOrder(FindOneOrderQuery findOneOrderQuery){
        return orderRepo.findByName(findOneOrderQuery.getName());
    }

}
