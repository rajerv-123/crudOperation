package com.areteminds.crudOperation.employee;
import com.areteminds.crudOperation.department.Department;
import com.areteminds.crudOperation.role.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="emp")
public class Employee {
    @Id
    private String empId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int salary;
    private String address;
    private String phoneNumber;
    private String technology;
    @OneToOne
    @JoinColumn(name = "deptId")
    private Department department;
    private String status;
    private String createdBy;
    private String token;
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "emp_roles",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
        return authorities;
    }

    private Date createdAt;
    private Date updatedAt;
    private String updateBy;
}


