package com.areteminds.crudOperation.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public interface EmployeeController {
    @PostMapping("/save")
    ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO); //learn ? , @Valid , ResponceEntity
    @PutMapping("/employees/{id}")
    ResponseEntity<?> updateEmployee(@PathVariable(value = "id", required = true) String empId, @Valid @RequestBody EmployeeDTO employeeDTO);
    @GetMapping("/employees/{id}")
    ResponseEntity<?> getEmployee(@PathVariable(value = "id", required = false) String empId);
    @GetMapping("/employees")
    ResponseEntity<?> getAllEmployee();
    @DeleteMapping("/employee/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable(value = "id", required = false) String empId);
}
