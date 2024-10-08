package com.dvh.employee_management.service;

import com.dvh.employee_management.dto.entity.EmployeeDto;
import com.dvh.employee_management.dto.request.EmployeeRequest;
import com.dvh.employee_management.dto.request.LoginRequest;
import com.dvh.employee_management.dto.request.RefreshTokenRequest;
import com.dvh.employee_management.dto.response.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployee(String empNo);

    EmployeeDto addEmployee(EmployeeRequest emp);

    EmployeeDto updateEmployee(EmployeeRequest emp);

    void deleteEmployee(String empNo);

    List<EmployeeDto> getAllEmployees();

    void uploadImage(String empNo,MultipartFile file);

    TokenResponse refreshToken(String refreshToken);
    TokenResponse login(LoginRequest request);
}
