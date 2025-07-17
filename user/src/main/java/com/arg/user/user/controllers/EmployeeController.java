package com.arg.user.user.controllers;

import com.arg.user.user.dto.EmployeeDto;
import com.arg.user.user.entities.Employee;
import com.arg.user.user.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody EmployeeDto dto) {
        Employee saved = employeeService.createEmployee(dto);
        return ResponseEntity.ok(saved);
    }
}

