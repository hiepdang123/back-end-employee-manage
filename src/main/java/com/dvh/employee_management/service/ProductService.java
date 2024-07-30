package com.dvh.employee_management.service;

import com.dvh.employee_management.dto.entity.EmployeeDto;
import com.dvh.employee_management.dto.entity.ProductDto;
import com.dvh.employee_management.dto.request.EmployeeRequest;
import com.dvh.employee_management.dto.request.GetProductByIdsRequest;
import com.dvh.employee_management.dto.request.LoginRequest;
import com.dvh.employee_management.dto.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ProductService {
    ProductDto getProduct(int id);

    ProductDto addProduct(ProductRequest productRequest);

    ProductDto updateProduct(int id,ProductRequest productRequest);

    void deleteProduct(int id);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductByIds(Set<Integer> request);
}
