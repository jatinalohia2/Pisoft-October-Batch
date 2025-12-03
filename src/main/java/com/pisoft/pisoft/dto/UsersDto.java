package com.pisoft.pisoft.dto;

import com.pisoft.pisoft.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private String name;
    private String email;
    private Set<Roles> roles;
}
