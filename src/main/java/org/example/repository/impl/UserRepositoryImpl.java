package org.example.repository.impl;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> findAll() {
        return List.of();
    }
}
