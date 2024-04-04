package org.example.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User fromDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getAge(),
                userDto.getUserRole()
        );
    }

    public UserDto fromUserToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getAge(),
                user.getUserRole()
        );
    }

    public User fromEntityToUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getAge(),
                userEntity.getUserRole()
        );
    }

    public UserEntity fromUserToEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getAge(),
                user.getUserRole()
        );
    }
}
