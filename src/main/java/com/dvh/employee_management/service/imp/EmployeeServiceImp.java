package com.dvh.employee_management.service.imp;

import com.dvh.employee_management.constant.Constants;
import com.dvh.employee_management.dto.entity.EmployeeDto;
import com.dvh.employee_management.dto.request.EmployeeRequest;
import com.dvh.employee_management.dto.request.LoginRequest;
import com.dvh.employee_management.exception.EmployeeException;
import com.dvh.employee_management.model.Employee;
import com.dvh.employee_management.repository.EmployeeRepository;
import com.dvh.employee_management.service.EmployeeService;
import com.dvh.employee_management.service.FileStorageService;
import com.dvh.employee_management.user.CustomUserDetails;
import com.dvh.employee_management.utils.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto getEmployee(String empNo) {
        Optional<Employee> employeeOptional=employeeRepository.findById(empNo);
        if(!employeeOptional.isPresent()){
            throw new EmployeeException(Constants.ErrorMessageEmployeeValidation.NOT_FIND_EMPLOYEE_BY_EMPNO+empNo);
        }
        EmployeeDto employeeDto=modelMapper.map(employeeOptional.get(),EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public EmployeeDto addEmployee(EmployeeRequest emp) {
        Optional<Employee> employeeOptional=employeeRepository.findById(emp.getEmpNo());
        if(employeeOptional.isPresent()){
            throw new EmployeeException(Constants.ErrorMessageEmployeeValidation.EXIST_EMPLOYEE+emp.getEmpNo());
        }
        Employee employee=modelMapper.map(emp,Employee.class);
        employee.setPassword(passwordEncoder.encode(emp.getPassword()));
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeRequest emp) {
        Optional<Employee> employeeOptional=employeeRepository.findById(emp.getEmpNo());
        if(!employeeOptional.isPresent()){
            throw new EmployeeException(Constants.ErrorMessageEmployeeValidation.NOT_FIND_EMPLOYEE_BY_EMPNO+emp.getEmpNo());
        }
        String password=employeeOptional.get().getPassword();
        Employee employee=modelMapper.map(emp,Employee.class);
        employee.setPassword(password);
        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(String empNo) {
        Optional<Employee> employeeOptional=employeeRepository.findById(empNo);
        if(!employeeOptional.isPresent()){
            throw new EmployeeException(Constants.ErrorMessageEmployeeValidation.NOT_FIND_EMPLOYEE_BY_EMPNO+empNo);
        }
        employeeRepository.deleteById(empNo);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> list=employeeRepository.findAll();
        List<EmployeeDto> dtoList=new ArrayList<>();
        for (Employee employee:list){
            EmployeeDto dto=modelMapper.map(employee,EmployeeDto.class);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public void uploadImage(String empNo,MultipartFile file) {
        try {
            Optional<Employee> employeeOptional=employeeRepository.findById(empNo);
            if(!employeeOptional.isPresent()){
                throw new EmployeeException(Constants.ErrorMessageEmployeeValidation.NOT_FIND_EMPLOYEE_BY_EMPNO+empNo);
            }
            Employee employee=employeeOptional.get();
            String filename=fileStorageService.storeFile(file);
            employee.setPhotoUrl(filename);
            employeeRepository.save(employee);
        }catch (Exception e){
            throw new EmployeeException("Thêm ảnh thất bại");
        }
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmpNo(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(new CustomUserDetails(modelMapper.map(request,Employee.class)));
    }
}
