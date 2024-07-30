package com.dvh.employee_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private String empNo;
    private String empName;
    private String password;
    private String position;
    private String photoUrl;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
