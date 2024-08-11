package com.dvh.employee_management.repository;

import com.dvh.employee_management.model.Order;
import com.dvh.employee_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
