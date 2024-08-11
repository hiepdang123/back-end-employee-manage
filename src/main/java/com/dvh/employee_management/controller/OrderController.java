package com.dvh.employee_management.controller;

import com.dvh.employee_management.dto.entity.OrderDto;
import com.dvh.employee_management.dto.request.OrderRequest;
import com.dvh.employee_management.dto.response.BaseResponse;
import com.dvh.employee_management.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // URL:
    // http://localhost:8080/orders
    @GetMapping("")
    public ResponseEntity<?> getOrders() {
        List<OrderDto> list = orderService.getAllOrders();
        return BaseResponse.successListData(list,list.size());
    }

    // URL:
    // http://localhost:8080/order/{empNo}
    @GetMapping("{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") int id) {
        OrderDto dto= orderService.getOrder(id);
        return BaseResponse.successData(dto);
    }

    // URL:
    // http://localhost:8080/order

    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestBody @Valid OrderRequest request,
                                      @RequestHeader("Authorization") String jwt) {

        return BaseResponse.successData(orderService.addOrder(jwt,request));
    }

    // URL:
    // http://localhost:8080/order
//    @PutMapping("")
//    public ResponseEntity<?> updateOrder(@RequestBody @Valid OrderRequest emp) {
//
//        System.out.println("(Service Side) Editing order: " + emp.getEmpNo());
//
//        return BaseResponse.successData(orderService.updateOrder(emp));
//    }

    // URL:
    // http://localhost:8080/order/{empNo}
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") int id) {

        System.out.println("(Service Side) Deleting order: " + id);

        orderService.deleteOrder(id);
        return BaseResponse.successData("Xóa thành công order có empNo là: "+id);
    }
}