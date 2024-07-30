package com.dvh.employee_management.controller;

import com.dvh.employee_management.dto.entity.EmployeeDto;
import com.dvh.employee_management.dto.entity.ProductDto;
import com.dvh.employee_management.dto.request.EmployeeRequest;
import com.dvh.employee_management.dto.request.GetProductByIdsRequest;
import com.dvh.employee_management.dto.request.ProductRequest;
import com.dvh.employee_management.dto.response.BaseResponse;
import com.dvh.employee_management.service.EmployeeService;
import com.dvh.employee_management.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    // URL:
    // http://localhost:8080/employees
    @GetMapping("/")
    public ResponseEntity<?> getProducts() {
        List<ProductDto> list = productService.getAllProducts();
        return BaseResponse.successListData(list,list.size());
    }

    // URL:
    // http://localhost:8080/employee/{empNo}
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
        ProductDto dto= productService.getProduct(id);
        return BaseResponse.successData(dto);
    }

    @GetMapping("/ids")
    public ResponseEntity<?> getProductByIds(@RequestParam Set<Integer> ids) {
        List<ProductDto> dtoList= productService.getProductByIds(ids);
        return BaseResponse.successListData(dtoList,dtoList.size());
    }

    // URL:
    // http://localhost:8080/employee

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        return BaseResponse.successData(productService.addProduct(productRequest));
    }

    // URL:
    // http://localhost:8080/employee
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id,
                                            @RequestBody @Valid ProductRequest productRequest) {
        return BaseResponse.successData(productService.updateProduct(id,productRequest));
    }

    // URL:
    // http://localhost:8080/employee/{empNo}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        productService.deleteProduct(id);
        return BaseResponse.successData("Xóa thành công employee có empNo là: "+id);
    }
}