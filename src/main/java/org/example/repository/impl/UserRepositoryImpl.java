package org.example.repository.impl;

import org.example.entity.User;
import org.example.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> findAll() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Syed Raza");
        return List.of(user);
    }

    @Override
    public User findById() {
        return null;
    }
}
