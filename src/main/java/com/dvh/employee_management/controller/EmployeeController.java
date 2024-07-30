package com.dvh.employee_management.controller;

import com.dvh.employee_management.dto.entity.EmployeeDto;
import com.dvh.employee_management.dto.request.EmployeeRequest;
import com.dvh.employee_management.dto.response.BaseResponse;
import com.dvh.employee_management.service.EmployeeService;
import com.dvh.employee_management.service.FileStorageService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees/")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // URL:
    // http://localhost:8080/employees
    @GetMapping("")
    public ResponseEntity<?> getEmployees() {
        List<EmployeeDto> list = employeeService.getAllEmployees();
        return BaseResponse.successListData(list,list.size());
    }

    // URL:
    // http://localhost:8080/employee/{empNo}
    @GetMapping("{empNo}")
    public ResponseEntity<?> getEmployee(@PathVariable("empNo") String empNo) {
        EmployeeDto dto= employeeService.getEmployee(empNo);
        return BaseResponse.successData(dto);
    }

    // URL:
    // http://localhost:8080/employee

    @PostMapping("")
    public ResponseEntity<?> addEmployee(@RequestBody @Valid EmployeeRequest emp) {

        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());

        return BaseResponse.successData(employeeService.addEmployee(emp));
    }

    // URL:
    // http://localhost:8080/employee
    @PutMapping("")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid EmployeeRequest emp) {

        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());

        return BaseResponse.successData(employeeService.updateEmployee(emp));
    }

    // URL:
    // http://localhost:8080/employee/{empNo}
    @DeleteMapping("{empNo}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("empNo") String empNo) {

        System.out.println("(Service Side) Deleting employee: " + empNo);

        employeeService.deleteEmployee(empNo);
        return BaseResponse.successData("Xóa thành công employee có empNo là: "+empNo);
    }
}