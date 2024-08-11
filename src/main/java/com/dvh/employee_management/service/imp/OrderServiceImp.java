package com.dvh.employee_management.service.imp;

import com.dvh.employee_management.constant.Constants;
import com.dvh.employee_management.dto.entity.OrderDto;
import com.dvh.employee_management.dto.request.OrderItemRequest;
import com.dvh.employee_management.dto.request.OrderRequest;
import com.dvh.employee_management.exception.EmployeeException;
import com.dvh.employee_management.model.Employee;
import com.dvh.employee_management.model.Order;
import com.dvh.employee_management.model.OrderItem;
import com.dvh.employee_management.model.Product;
import com.dvh.employee_management.repository.EmployeeRepository;
import com.dvh.employee_management.repository.OrderItemRepository;
import com.dvh.employee_management.repository.OrderRepository;
import com.dvh.employee_management.repository.ProductRepository;
import com.dvh.employee_management.service.OrderService;
import com.dvh.employee_management.service.OrderService;
import com.dvh.employee_management.user.UserService;
import com.dvh.employee_management.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderDto getOrder(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
//            throw new EmployeeException(Constants.ErrorMessageOrderValidation.NOT_FIND_PRODUCT_BY_ID+id);
        }
        OrderDto orderDto = modelMapper.map(orderOptional.get(), OrderDto.class);
        return orderDto;
    }

    @Override
    public OrderDto addOrder(String jwt, OrderRequest orderRequest) {
        SecurityContext context = SecurityContextHolder.getContext();
        String empNo = context.getAuthentication().getName();
        Optional<Employee> employeeOptional = employeeRepository.findById(empNo);
        List<OrderItem> list = new ArrayList<>();
        Order order = Order.builder()
                .orderDate(new Date())
                .customer(employeeOptional.get())
                .build();
        order=orderRepository.save(order);
        for (OrderItemRequest item : orderRequest.getOrders()) {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .quantity(item.getQuantity())
                    .price(item.getPrice())
                    .name(item.getName())
                    .build();
            Optional<Product> productOptional = productRepository.findById(item.getId());
            Product product = productOptional.get();
            product.setAmount(product.getAmount() - item.getQuantity());
            productRepository.save(product);

            list.add(orderItem);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
        order.setOrderItems(list);
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

//    @Override
//    public OrderDto updateOrder(int id,OrderRequest emp) {
//        Optional<Order> orderOptional=orderRepository.findById(id);
//        if(!orderOptional.isPresent()){
////            throw new EmployeeException(Constants.ErrorMessageOrderValidation.NOT_FIND_PRODUCT_BY_ID+id);
//        }
//        Order order=modelMapper.map(emp,Order.class);
//        order.setId(id);
//        return modelMapper.map(orderRepository.save(order),OrderDto.class);
//    }

    @Override
    public void deleteOrder(int id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
//            throw new EmployeeException(Constants.ErrorMessageOrderValidation.NOT_FIND_PRODUCT_BY_ID+id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> list = orderRepository.findAll();
        List<OrderDto> dtoList = new ArrayList<>();
        for (Order order : list) {
            OrderDto dto = modelMapper.map(order, OrderDto.class);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
