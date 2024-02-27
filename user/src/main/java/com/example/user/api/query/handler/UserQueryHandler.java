package com.example.user.api.query.handler;

import com.example.core.domain.ACUSer;
import com.example.core.querys.FindUserQuery;
import lombok.RequiredArgsConstructor;
 import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserQueryHandler {

    @QueryHandler
    public ACUSer handle(FindUserQuery query) {
        return ACUSer.builder()
                .id(UUID.randomUUID().toString())
                .name(query.getUsername())
                .title("this is a user")
                .build();
    }
}
