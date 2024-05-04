package org.example.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.springframework.security.core.userdetails.User.withUsername;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User createUser(User user) {
        if (user == null) {
            throw new NullPointerException("Пользователь не указан");
        }

        if (userRepository.existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }
        UserEntity userEntity = userMapper.fromUserToEntity(user);

        return userMapper.fromEntityToUser(userRepository.save(userEntity));
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(userMapper::fromEntityToUser)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с таким id не найден"));
    }

    public User getByUsername(String login) {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Пользователь логином " + login + " не найден");
        }

        return userMapper.fromEntityToUser(userEntity);
    }

    public UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(login);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Пользователь с таким логином не найдет");
        }
        return withUsername(userEntity.getLogin())
                .password((userEntity.getPassword()))
                .authorities(new SimpleGrantedAuthority(userEntity.getUserRole().name()))
                .build();
    }
}


