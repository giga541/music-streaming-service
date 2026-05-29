package com.solvd.musicstreamingservice.decorator;

import com.solvd.musicstreamingservice.model.User;
import com.solvd.musicstreamingservice.persistence.UserRepository;

import java.util.List;
import java.util.Optional;

public abstract class UserRepositoryDecorator implements UserRepository {

    protected final UserRepository userRepository;

    public UserRepositoryDecorator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllPremium() {
        return userRepository.findAllPremium();
    }

    @Override
    public List<User> findAllWithDetails() {
        return userRepository.findAllWithDetails();
    }
}