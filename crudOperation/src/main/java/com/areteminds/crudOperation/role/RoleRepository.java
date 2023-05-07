package com.areteminds.crudOperation.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,String> {
    @Query("SELECT r FROM Role r WHERE r.roleId = :roleId")
    Role findByRoleId(@Param("roleId") String roleId);
    @Query("SELECT r FROM Role r WHERE r.roleName = :roleName")
    Role findByRoleName(@Param("roleName") String roleName);
}

