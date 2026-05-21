package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Review;
import com.solvd.musicstreamingservice.persistence.ReviewRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ReviewMapperImpl implements ReviewRepository {

    @Override
    public Review create(Review review) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.insert("com.solvd.musicstreamingservice.persistence.ReviewRepository.create", review);
        }
        return review;
    }

    @Override
    public Optional<Review> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.selectOne(
                    "com.solvd.musicstreamingservice.persistence.ReviewRepository.findById", id));
        }
    }

    @Override
    public List<Review> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList("com.solvd.musicstreamingservice.persistence.ReviewRepository.findAll");
        }
    }

    @Override
    public Review update(Review review) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.update("com.solvd.musicstreamingservice.persistence.ReviewRepository.update", review);
        }
        return review;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.delete("com.solvd.musicstreamingservice.persistence.ReviewRepository.delete", id);
        }
    }

    @Override
    public List<Review> findBySongId(Long songId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.ReviewRepository.findBySongId", songId);
        }
    }

    @Override
    public List<Review> findByRatingGreaterThan(int rating) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.ReviewRepository.findByRatingGreaterThan", rating);
        }
    }
}