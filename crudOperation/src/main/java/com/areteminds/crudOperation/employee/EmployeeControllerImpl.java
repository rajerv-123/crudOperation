package com.areteminds.crudOperation.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeControllerImpl implements EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;


    @Override
    public ResponseEntity<?> saveEmployee(EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.saveEmployees(employeeDTO));
    }

    @Override
    public ResponseEntity<?> updateEmployee(String empId, EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployees(empId, employeeDTO));
    }

    @Override
    public ResponseEntity<?> getEmployee(String empId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployees(empId));
    }

    @Override
    public ResponseEntity<?> getAllEmployee() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @Override
    public ResponseEntity<?> deleteEmployee(String empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.ok().body("Employee Deleted successfully");
    }
}