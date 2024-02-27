package com.example.eventdriven.api.query.rest;


import com.example.eventdriven.domain.Order;
import com.example.eventdriven.api.query.querys.FindOneOrderQuery;
import com.example.eventdriven.api.query.querys.FindOrdersQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderResource {

    private final  QueryGateway queryGateway;

    @GetMapping("/products")
    public List<Order> getProducts(){
        FindOrdersQuery findOrdersQuery = new FindOrdersQuery();
        List<Order> orders = queryGateway.query(findOrdersQuery, ResponseTypes.multipleInstancesOf(Order.class)).join();
        return orders;
    }
    @GetMapping("/products/{name}")
    public Order getProductByName(@PathVariable String name){
        FindOneOrderQuery findOneOrderQuery = new FindOneOrderQuery(name);
        Order order = queryGateway.query(findOneOrderQuery, ResponseTypes.optionalInstanceOf(Order.class)).join().get();
        return order;
    }

}
