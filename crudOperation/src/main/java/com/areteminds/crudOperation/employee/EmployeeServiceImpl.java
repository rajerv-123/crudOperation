package com.areteminds.crudOperation.employee;

import com.areteminds.crudOperation.Exception.GenericException;
import com.areteminds.crudOperation.Exception.ResourceNotFoundException;
import com.areteminds.crudOperation.department.Department;
import com.areteminds.crudOperation.department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Employee saveEmployees(EmployeeDTO employeeDTO) {
         Department department = departmentRepository.findById(employeeDTO.getDeptId()).orElseThrow(() -> new ResourceNotFoundException("Department with this Id is not found"));

        Employee savedEmployee = new Employee();
        savedEmployee.setEmpId(UUID.randomUUID().toString());
        savedEmployee.setFirstName(employeeDTO.getFirstName());
        savedEmployee.setLastName(employeeDTO.getLastName());
        savedEmployee.setSalary(employeeDTO.getSalary());
        savedEmployee.setStatus("ACTIVE");

      //savedEmployee.setDeptId(employeeDTO.getDeptId());
        savedEmployee.setAddress(employeeDTO.getAddress());
        savedEmployee.setEmail(employeeDTO.getEmail());
        savedEmployee.setUpdatedAt(new Date());
        savedEmployee.setCreatedAt(new Date());
        savedEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        savedEmployee.setTechnology(employeeDTO.getTechnology());
        savedEmployee.setCreatedBy(employeeDTO.getFirstName());
        savedEmployee.setDepartment(department);
        try {
            employeeRepository.save(savedEmployee);
        } catch (Exception e) {
            throw new GenericException("Employee Not Saved");
        }
        return savedEmployee;
    }
    @Override
    public Employee updateEmployees(String empId, EmployeeDTO employeeDTO) {

        Employee updatedEmployee = new Employee();
        updatedEmployee = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("employee id for this not found"));
        updatedEmployee.setFirstName(employeeDTO.getFirstName());
        updatedEmployee.setLastName(employeeDTO.getLastName());
        updatedEmployee.setSalary(employeeDTO.getSalary());
        updatedEmployee.setStatus("ACTIVE");

       // updatedEmployee.setDeptId(updatedEmployee.getDepartment().getDeptId());
        updatedEmployee.setAddress(employeeDTO.getAddress());
        updatedEmployee.setEmail(employeeDTO.getEmail());
        updatedEmployee.setUpdatedAt(new Date());
        updatedEmployee.setCreatedAt(new Date());
        updatedEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        updatedEmployee.setTechnology(employeeDTO.getTechnology());
        updatedEmployee.setCreatedBy(employeeDTO.getFirstName());
        // employee.setDepartment(department);
        try {
            employeeRepository.save(updatedEmployee);
        } catch (Exception e) {
            throw new GenericException("Employee Not Saved");
        }
        return updatedEmployee;
    }
    @Override
    public Optional<Employee> getEmployees(String empId) { //learn optional
        return employeeRepository.findById(empId);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(String empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (!employee.isPresent()) {
            throw new GenericException("Employee with given id not found");
        }
        Employee employee1 = employee.get();
        if (employee1.getStatus() == null) {
            throw new GenericException("Employee with given id is null");
        }
        if (employee1.getStatus().equals("INACTIVE")) {
            throw new GenericException("Employee with given id is already INACTIVE");
        }
        employee1.setStatus("INACTIVE");
        employeeRepository.save(employee1);
    }
}