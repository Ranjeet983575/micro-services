package com.arg.user.user.services.impl;

import com.arg.user.user.dto.EmployeeDto;
import com.arg.user.user.entities.Contact;
import com.arg.user.user.entities.Department;
import com.arg.user.user.entities.Employee;
import com.arg.user.user.respositories.ContactRepository;
import com.arg.user.user.respositories.DepartmentRepository;
import com.arg.user.user.respositories.EmployeeRepository;
import com.arg.user.user.services.ContactService;
import com.arg.user.user.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ContactService contactService;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Employee createEmployee(EmployeeDto dto) {

        Department department = new Department();
        department.setDName(dto.getDName());
        departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(department);
        Contact contact = contactService.saveContact(dto);
        employee.setContact(contact);

        return employeeRepository.save(employee);
    }


}
