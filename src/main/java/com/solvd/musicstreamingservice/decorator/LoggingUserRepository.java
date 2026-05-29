package com.solvd.musicstreamingservice.decorator;

import com.solvd.musicstreamingservice.model.User;
import com.solvd.musicstreamingservice.persistence.UserRepository;

import java.util.List;
import java.util.Optional;

public class LoggingUserRepository extends UserRepositoryDecorator {

    public LoggingUserRepository(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public void create(User user) {
        System.out.println("Creating user: " + user.getUsername());
        userRepository.create(user);
        System.out.println("Created user with id: " + user.getId());
    }

    @Override
    public Optional<User> findById(Long id) {
        System.out.println("Finding user by id: " + id);
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        System.out.println("Finding all users");
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        System.out.println("Updating user: " + user.getId());
        userRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        System.out.println("Deleting user: " + id);
        userRepository.delete(id);
    }
}