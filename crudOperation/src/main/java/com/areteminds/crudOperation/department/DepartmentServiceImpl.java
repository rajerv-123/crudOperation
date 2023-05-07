package com.areteminds.crudOperation.department;

import com.areteminds.crudOperation.Exception.GenericException;
import com.areteminds.crudOperation.Exception.ResourceNotFoundException;
import com.areteminds.crudOperation.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Department saveDepartment(DepartmentDTO departmentDTO) {
        Department saveDepartment = new Department();
        saveDepartment.setDeptId(UUID.randomUUID().toString());
        saveDepartment.setDescription(departmentDTO.getDescription());
        saveDepartment.setName(departmentDTO.getName());
        saveDepartment.setStatus("ACTIVE");
        saveDepartment.setUpdatedAt(new Date());
        saveDepartment.setCreatedAt(new Date());
        saveDepartment.setUpdatedBy(departmentDTO.getName());
        try {
            departmentRepository.save(saveDepartment);
        } catch (Exception e) {
            throw new GenericException("Department not saved");
        }
        return saveDepartment;
    }
    @Override
    public Optional<Department> getDepartment(String deptId) {
        return departmentRepository.findById(deptId);
    }
    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(String deptId, DepartmentDTO departmentDTO) {
        Department updateDepartment = new Department();
        updateDepartment = departmentRepository.findById(deptId).orElseThrow(() -> new ResourceNotFoundException("department id for this not found"));
        updateDepartment.setName(departmentDTO.getName());
        updateDepartment.setDescription(departmentDTO.getDescription());
        updateDepartment.setStatus("ACTIVE");
        updateDepartment.setUpdatedAt(new Date());
        updateDepartment.setUpdatedBy(departmentDTO.getName());
        updateDepartment.setCreatedAt(new Date());
        try {
            departmentRepository.save(updateDepartment);
        } catch (Exception e) {
            throw new GenericException("Department not found");
        }
        return updateDepartment;
    }
    @Override
    public void deleteDepartment(String deptId) {
        Optional<Department> department = departmentRepository.findById(deptId);
        if (!department.isPresent()) {
            throw new GenericException("department with given id not found");
        }
        Department department1 = department.get();
        if (department1.getStatus() == null) {
            throw new GenericException("Employee with given id is null");
        }
        if (department1.getStatus().equals("INACTIVE")) {
            throw new GenericException("Employee with given id is already INACTIVE");
        }
        department1.setStatus("INACTIVE");
        departmentRepository.save(department1);
    }

}
//service interface method,controller mein service wala method call ,implement methof in serviceimpl