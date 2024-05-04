package org.example.security;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequest {
    @NotBlank
    private final String login;
    @NotBlank
    private final String password;
}
