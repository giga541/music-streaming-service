package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Review;
import com.solvd.musicstreamingservice.persistence.ReviewRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ReviewMapperImpl implements ReviewRepository {

    @Override
    public void create(Review review) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(ReviewRepository.class).create(review);
        }
    }

    @Override
    public void update(Review review) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(ReviewRepository.class).update(review);
        }
    }

    @Override
    public Optional<Review> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ReviewRepository.class).findById(id);
        }
    }

    @Override
    public List<Review> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ReviewRepository.class).findAll();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(ReviewRepository.class).delete(id);
        }
    }

    @Override
    public List<Review> findBySongId(Long songId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ReviewRepository.class).findBySongId(songId);
        }
    }

    @Override
    public List<Review> findByRatingGreaterThan(int rating) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ReviewRepository.class).findByRatingGreaterThan(rating);
        }
    }
}