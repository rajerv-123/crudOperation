package com.areteminds.crudOperation.department;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public interface DepartmentController {

    @PostMapping("/dept")
    ResponseEntity<?> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO);

    @GetMapping("department/{id}")
    ResponseEntity<?> getDepartment(@PathVariable(value = "id", required = false) String deptId);

    @GetMapping("/department")
    ResponseEntity<?> getAllDepartment();

    @PutMapping("/department/{id}")
    ResponseEntity<?> updateDepartment(@PathVariable(value = "id", required = true) String deptId, @Valid @RequestBody DepartmentDTO departmentDTO);

    @DeleteMapping("/department/{id}")
    ResponseEntity<?> deleteDepartment(@PathVariable(value = "id", required = false) String deptId);
}
