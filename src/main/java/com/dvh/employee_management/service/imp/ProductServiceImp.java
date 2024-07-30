package com.dvh.employee_management.service.imp;

import com.dvh.employee_management.constant.Constants;
import com.dvh.employee_management.dto.entity.EmployeeDto;
import com.dvh.employee_management.dto.entity.ProductDto;
import com.dvh.employee_management.dto.request.GetProductByIdsRequest;
import com.dvh.employee_management.dto.request.ProductRequest;
import com.dvh.employee_management.exception.EmployeeException;
import com.dvh.employee_management.model.Employee;
import com.dvh.employee_management.model.Product;
import com.dvh.employee_management.repository.ProductRepository;
import com.dvh.employee_management.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto getProduct(int id) {
        Optional<Product> productOptional=productRepository.findById(id);
        if(!productOptional.isPresent()){
            throw new EmployeeException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID+id);
        }
        ProductDto productDto=modelMapper.map(productOptional.get(),ProductDto.class);
        return productDto;
    }

    @Override
    public ProductDto addProduct(ProductRequest productRequest) {
        Product product=modelMapper.map(productRequest,Product.class);
        return modelMapper.map(productRepository.save(product),ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(int id,ProductRequest emp) {
        Optional<Product> productOptional=productRepository.findById(id);
        if(!productOptional.isPresent()){
            throw new EmployeeException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID+id);
        }
        Product product=modelMapper.map(emp,Product.class);
        product.setId(id);
        return modelMapper.map(productRepository.save(product),ProductDto.class);
    }

    @Override
    public void deleteProduct(int id) {
        Optional<Product> productOptional=productRepository.findById(id);
        if(!productOptional.isPresent()){
            throw new EmployeeException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID+id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> list=productRepository.findAll();
        List<ProductDto> dtoList=new ArrayList<>();
        for (Product product:list){
            ProductDto dto=modelMapper.map(product,ProductDto.class);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<ProductDto> getProductByIds(Set<Integer> request) {
        List<Product> list=productRepository.findAllById(request);
        List<ProductDto> dtoList=new ArrayList<>();
        for (Product product:list){
            ProductDto dto=modelMapper.map(product,ProductDto.class);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
