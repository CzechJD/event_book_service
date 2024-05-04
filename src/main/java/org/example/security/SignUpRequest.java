package org.example.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequest {

    private final String login;
    private final String password;
    private final Integer age;
}
