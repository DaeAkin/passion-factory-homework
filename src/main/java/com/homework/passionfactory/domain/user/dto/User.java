package com.homework.passionfactory.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private Integer number;
}
