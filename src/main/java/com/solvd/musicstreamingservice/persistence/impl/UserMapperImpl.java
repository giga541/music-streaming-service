package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.User;
import com.solvd.musicstreamingservice.persistence.UserRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class UserMapperImpl implements UserRepository {

    @Override
    public User create(User user) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(UserRepository.class).create(user);
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(UserRepository.class).findById(id);
        }
    }

    @Override
    public List<User> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(UserRepository.class).findAll();
        }
    }

    @Override
    public User update(User user) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(UserRepository.class).update(user);
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(UserRepository.class).delete(id);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(UserRepository.class).findByEmail(email);
        }
    }

    @Override
    public List<User> findAllPremium() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(UserRepository.class).findAllPremium();
        }
    }

    @Override
    public List<User> findAllWithDetails() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(UserRepository.class).findAllWithDetails();
        }
    }
}