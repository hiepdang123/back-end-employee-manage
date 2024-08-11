package com.dvh.employee_management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequest {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String imgUrl;
}
