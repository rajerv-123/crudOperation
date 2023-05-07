package com.areteminds.crudOperation.auth;

import com.areteminds.crudOperation.employee.Employee;
import com.areteminds.crudOperation.employee.EmployeeRepository;
import com.areteminds.crudOperation.employee.EmployeeService;
import com.areteminds.crudOperation.role.Role;
import com.areteminds.crudOperation.role.RoleRepository;
import com.areteminds.crudOperation.security.jwt.JwtUtils;
import com.areteminds.crudOperation.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@RestController
public class AuthControllerImpl implements AuthController{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
       Employee employee = employeeRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));
        employee.setToken(jwt); // set the token on the user
        employeeRepository.save(employee); // saveOrganization the updated user
        return jwt;
    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (employeeRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (employeeRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Role role;
        if (signUpRequest.getRoleName() != null) {
            role = roleRepository.findByRoleName(signUpRequest.getRoleName());
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Role not provided!"));
        }

        if (!signUpRequest.getRoleName().equals("ROLE_USER")) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Only users with ROLE_USER role are allowed to register!"));
        }


        if (role == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Role!"));
        }


            Employee employee =  Employee.builder()
                    .empId(UUID.randomUUID().toString())
                    .createdAt(new Date())
                    .email(signUpRequest.getEmail())
                    .username(signUpRequest.getUsername())
                    .roles(Collections.singleton(role))
                    .phoneNumber(signUpRequest.getPhoneNumber())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .email(UUID.randomUUID().toString())
                   .roleId(role.getRoleId())
                    .roleName(role.getRoleName()).build();
            employeeRepository.save(employee);

            return new ResponseEntity<>(employee, HttpStatus.OK);



    }
}
