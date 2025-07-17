package com.arg.user.user.services;

import com.arg.user.user.dto.EmployeeDto;
import com.arg.user.user.entities.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    public Employee createEmployee(EmployeeDto dto);
}
