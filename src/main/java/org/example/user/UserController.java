package org.example.user;

import lombok.AllArgsConstructor;
import org.example.security.AuthenticationService;
import org.example.security.SignInRequest;
import org.example.security.SignUpRequest;
import org.example.security.jwt.JwtAuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<UserDto> registerUser(@RequestBody @Validated SignUpRequest request) {
        User registeredUser = authenticationService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.fromUserToDto(registeredUser));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) throws AccessDeniedException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(userMapper.fromUserToDto(user));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtAuthenticationResponse> getJwtToken(@RequestBody @Validated SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
