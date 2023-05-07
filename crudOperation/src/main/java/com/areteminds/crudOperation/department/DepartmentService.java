package com.areteminds.crudOperation.department;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DepartmentService {
  public Department saveDepartment(DepartmentDTO departmentDTO);
  public Optional<Department> getDepartment(String deptId);
  public List<Department> getAllDepartment();
  public Department updateDepartment(String deptId, DepartmentDTO departmentDTO);
  public void deleteDepartment(String deptId);
}
//entity,controller,controllerImpl,repository,service,serviceImpl,dto
