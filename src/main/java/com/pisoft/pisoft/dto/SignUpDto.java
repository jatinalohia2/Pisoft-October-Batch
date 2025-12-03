package com.pisoft.pisoft.dto;

import com.pisoft.pisoft.enums.Roles;
import com.pisoft.pisoft.enums.UserPermissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String name;
    private String email;
    private String password;
    private Set<Roles> roles;
    private Set<UserPermissions> permissions;

}
