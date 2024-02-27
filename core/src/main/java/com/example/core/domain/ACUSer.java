package com.example.core.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ACUSer {
    String id;
    String name;
    String title;
}
