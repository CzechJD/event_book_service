package org.example.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@AllArgsConstructor
public class User {
    public final Integer id;
    public final String login;
    public final String password;
    public final Integer age;
    public final UserRole userRole;

    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole.name()));
    }
}
