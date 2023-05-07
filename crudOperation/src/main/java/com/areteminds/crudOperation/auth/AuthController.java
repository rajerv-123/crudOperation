package com.areteminds.crudOperation.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public interface AuthController {
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest);
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest);
}
