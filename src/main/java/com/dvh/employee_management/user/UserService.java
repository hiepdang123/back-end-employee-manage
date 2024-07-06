package com.dvh.employee_management.user;

import com.dvh.employee_management.exception.DataNotFoundException;
import com.dvh.employee_management.model.Employee;
import com.dvh.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee user = employeeRepository.findByEmpNo(username);
        if (user == null) {
            throw new DataNotFoundException("Tài khoản hoặc mật khẩu không đúng");
        }
        return new CustomUserDetails(user);
    }
}
