package com.dvh.employee_management.controller;

import com.dvh.employee_management.dto.request.LoginRequest;
import com.dvh.employee_management.dto.response.BaseItemResponse;
import com.dvh.employee_management.dto.response.BaseResponse;
import com.dvh.employee_management.dto.response.TokenResponse;
import com.dvh.employee_management.service.EmployeeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest loginRequest){
        TokenResponse token=employeeService.login(loginRequest);

        ResponseCookie cookie=ResponseCookie.from("refresh-token")
                .value(token.getRefreshToken())
                .maxAge(Duration.ofDays(1))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();

        BaseItemResponse baseResponse=new BaseItemResponse<>();
        baseResponse.setSuccess("Bearer "+token.getAccessToken());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(baseResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken (@CookieValue(name = "refresh-token",required = false) String refreshToken)    {
        TokenResponse token=employeeService.refreshToken(refreshToken);

        ResponseCookie cookie=ResponseCookie.from("refresh-token")
                .value(token.getRefreshToken())
                .maxAge(Duration.ofDays(1))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();

        BaseItemResponse baseResponse=new BaseItemResponse<>();
        baseResponse.setSuccess("Bearer "+token.getAccessToken());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(baseResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout (HttpServletResponse response){
        Cookie cookie=new Cookie("refresh-token",null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return BaseResponse.successData("Đăng xuất thành công");
    }
}
