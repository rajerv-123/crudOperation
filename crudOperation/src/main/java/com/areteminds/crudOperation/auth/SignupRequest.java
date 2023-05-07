package com.areteminds.crudOperation.auth;


import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
        @NotBlank
        @Size(min = 3, max = 20)
        private String username;

        @NotBlank
        @Size(max = 50)
        @Email
        private String email;

        private String roleName;

        private String roleId;

        @NotBlank
        @Size(min = 6, max = 40)
        private String password;




        private Date createdAt;

        private String createdBy;

        private String phoneNumber;

        private String status;

    }
