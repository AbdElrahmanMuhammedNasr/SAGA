package com.example.eventdriven.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "service")
public record ServiceInfo(
        String env,
        String name,
        String createdBy,
        String createdAt,
        Map<String, String> author,
        List<String> url
) {
}
