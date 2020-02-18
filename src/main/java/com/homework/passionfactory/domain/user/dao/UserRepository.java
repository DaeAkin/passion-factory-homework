package com.homework.passionfactory.domain.user.dao;

import com.homework.passionfactory.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String username);
}
