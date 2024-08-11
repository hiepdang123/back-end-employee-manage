package com.dvh.employee_management.service;

import com.dvh.employee_management.dto.entity.OrderDto;
import com.dvh.employee_management.dto.request.OrderRequest;

import java.util.List;
import java.util.Set;

public interface OrderService {
    OrderDto getOrder(int id);

    OrderDto addOrder(String jwt,OrderRequest productRequest);

//    OrderDto updateOrder(int id,OrderRequest productRequest);

    void deleteOrder(int id);

    List<OrderDto> getAllOrders();
}
