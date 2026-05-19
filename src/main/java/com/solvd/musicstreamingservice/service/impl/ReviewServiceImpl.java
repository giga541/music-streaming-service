package com.solvd.musicstreamingservice.service.impl;

import com.solvd.musicstreamingservice.model.Review;
import com.solvd.musicstreamingservice.persistence.ReviewRepository;
import com.solvd.musicstreamingservice.persistence.impl.ReviewRepositoryImpl;
import com.solvd.musicstreamingservice.service.ReviewService;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl() {
        this.reviewRepository = new ReviewRepositoryImpl();
    }

    @Override
    public Review create(Review review) {
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        return reviewRepository.create(review);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review update(Review review) {
        return reviewRepository.update(review);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.delete(id);
    }

    @Override
    public List<Review> findBySongId(Long songId) {
        return reviewRepository.findBySongId(songId);
    }

    @Override
    public List<Review> findByRatingGreaterThan(int rating) {
        return reviewRepository.findByRatingGreaterThan(rating);
    }
}