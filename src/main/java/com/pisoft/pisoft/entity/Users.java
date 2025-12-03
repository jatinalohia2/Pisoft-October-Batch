package com.pisoft.pisoft.entity;

import com.pisoft.pisoft.enums.Roles;
import com.pisoft.pisoft.enums.UserPermissions;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String email;

    private String password;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserPermissions> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.stream()
                .map(roles1 -> authorities.add(new SimpleGrantedAuthority("ROLE_"+roles1.name())))
                .collect(Collectors.toSet());

        permissions.stream()
                .map(permissions -> authorities.add(new SimpleGrantedAuthority(permissions.name())))
                .collect(Collectors.toSet());

        return authorities;

    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


}

