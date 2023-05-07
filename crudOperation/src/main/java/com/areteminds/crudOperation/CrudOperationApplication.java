package com.areteminds.crudOperation;//package com.areteminds.crudOperation;
//import com.areteminds.crudOperation.role.Role;
//import com.areteminds.crudOperation.role.RoleRepository;
//import com.areteminds.crudOperation.utils.Constants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import javax.annotation.PostConstruct;
//@SpringBootApplication
//@EnableSwagger2
//public class CrudOperationApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(CrudOperationApplication.class, args);
//		System.out.println("CRUD Operation Spring boot Application");
//}
//    @Autowired
//    private RoleRepository repository;
//    @PostConstruct
//    public void init(){
//        try {
//            Role role_admin = Role.builder().roleId(Constants.role_admin_id).roleName("ROLE_ADMIN").build();
//            Role role_user = Role.builder().roleId(Constants.role_user_id).roleName("ROLE_USER").build();
//            repository.save(role_admin);
//            repository.save(role_user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


import com.areteminds.crudOperation.role.Role;
import com.areteminds.crudOperation.role.RoleRepository;
import com.areteminds.crudOperation.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataIntegrityViolationException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableSwagger2
public class CrudOperationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudOperationApplication.class, args);
        System.out.println("CRUD Operation Spring boot Application");
    }

    @Autowired
    private RoleRepository repository;

    @PostConstruct
    public void init() {
        try {
            Role role_admin = Role.builder().roleId(Constants.role_admin_id).roleName("ROLE_ADMIN").build();
            Role role_user = Role.builder().roleId(Constants.role_user_id).roleName("ROLE_USER").build();
            repository.save(role_admin);
            repository.save(role_user);
        } catch (DataIntegrityViolationException e) {
            // handle the exception here
            e.printStackTrace();
        }
    }
}