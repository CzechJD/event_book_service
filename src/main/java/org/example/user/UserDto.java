package org.example.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    public final Integer id;
    @NotBlank
    public final String login;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public final String password;
    @NotNull
    public final Integer age;
    public final UserRole userRole;
}
