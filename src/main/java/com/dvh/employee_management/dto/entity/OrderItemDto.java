package com.dvh.employee_management.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private int id;
    private String name;
    private int price;
    private int amount;
    private String imgUrl;
}
