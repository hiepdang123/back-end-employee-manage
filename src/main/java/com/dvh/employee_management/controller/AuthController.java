package com.dvh.employee_management.controller;

import com.dvh.employee_management.dto.request.LoginRequest;
import com.dvh.employee_management.dto.response.BaseResponse;
import com.dvh.employee_management.service.EmployeeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest loginRequest){
        String token=employeeService.login(loginRequest);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(24 * 60 * 60); // 1 day in seconds
        cookie.setPath("/");

        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getResponse().addCookie(cookie);
        return BaseResponse.successData("Bearer "+token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout (HttpServletResponse response){
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return BaseResponse.successData("Đăng xuất thành công");
    }
}
