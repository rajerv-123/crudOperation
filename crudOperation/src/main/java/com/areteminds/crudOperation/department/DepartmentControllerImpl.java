package com.areteminds.crudOperation.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentControllerImpl implements  DepartmentController{
    @Autowired
    private DepartmentService departmentService;


    @Override
    public ResponseEntity<?> saveDepartment(DepartmentDTO departmentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.saveDepartment(departmentDTO));
    }

    @Override
    public ResponseEntity<?> getDepartment(@PathVariable(value= "id", required = false) String deptId) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartment((deptId)));
    }
    @Override
    public ResponseEntity<?> getAllDepartment(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllDepartment());
    }

    @Override
    public ResponseEntity<?> updateDepartment(String deptId,DepartmentDTO departmentDTO){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(deptId ,departmentDTO));
    }
    @Override
    public ResponseEntity<?> deleteDepartment(String deptId){
        departmentService.deleteDepartment(deptId);
        return ResponseEntity.ok().body("department delete successfully");
    }

}

