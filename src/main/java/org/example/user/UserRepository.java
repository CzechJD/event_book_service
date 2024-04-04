package org.example.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByLogin(String login);

    UserEntity findByLogin(String login);
}
