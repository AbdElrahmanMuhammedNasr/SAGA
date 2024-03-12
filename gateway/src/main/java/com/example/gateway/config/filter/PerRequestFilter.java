package com.example.gateway.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Component
public class PerRequestFilter implements  GlobalFilter  {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.mutate().request(exchange.getRequest().mutate().header("request-id", UUID.randomUUID().toString()).build()).build();
        return chain.filter(exchange);
    }
}
