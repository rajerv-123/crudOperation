package com.areteminds.crudOperation.employee;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    public Employee saveEmployees(EmployeeDTO employeeDTO);

    public Employee updateEmployees(String empId,EmployeeDTO employeeDTO);

    public Optional<Employee> getEmployees(String empId);

    public List<Employee> getAllEmployees();

    public void deleteEmployee(String empId);
}