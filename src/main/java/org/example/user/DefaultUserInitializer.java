package org.example.user;

import lombok.Data;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Data
public class DefaultUserInitializer {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        if (userRepository.findByLogin("admin") == null) {
            User admin = User.builder()
                    .id(1)
                    .login("admin")
                    .password(passwordEncoder.encode("admin"))
                    .age(30)
                    .userRole(UserRole.ADMIN)
                    .build();
            userService.createUser(admin);
        }

        if (userRepository.findByLogin("user") == null) {
            User user = User.builder()
                    .id(2)
                    .login("user")
                    .password(passwordEncoder.encode("user"))
                    .age(20)
                    .userRole(UserRole.USER)
                    .build();
            userService.createUser(user);
        }
    }
}
