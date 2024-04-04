package org.example.security;

import lombok.Data;
import org.example.security.jwt.JwtAuthenticationResponse;
import org.example.security.jwt.JwtService;
import org.example.user.User;
import org.example.user.UserRole;
import org.example.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;

    public User signUp(SignUpRequest request) {
        var user = User.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.USER)
                .age(request.getAge())
                .build();

        return userService.createUser(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));

        var user = userService.getByUsername(request.getLogin());
        var jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt);
    }
}
