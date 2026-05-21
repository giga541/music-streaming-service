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
            session.insert("com.solvd.musicstreamingservice.persistence.UserRepository.create", user);
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.selectOne(
                    "com.solvd.musicstreamingservice.persistence.UserRepository.findById", id));
        }
    }

    @Override
    public List<User> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList("com.solvd.musicstreamingservice.persistence.UserRepository.findAll");
        }
    }

    @Override
    public User update(User user) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.update("com.solvd.musicstreamingservice.persistence.UserRepository.update", user);
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.delete("com.solvd.musicstreamingservice.persistence.UserRepository.delete", id);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.selectOne(
                    "com.solvd.musicstreamingservice.persistence.UserRepository.findByEmail", email));
        }
    }

    @Override
    public List<User> findAllPremium() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList("com.solvd.musicstreamingservice.persistence.UserRepository.findAllPremium");
        }
    }

    @Override
    public List<User> findAllWithDetails() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList("com.solvd.musicstreamingservice.persistence.UserRepository.findAllWithDetails");
        }
    }
}