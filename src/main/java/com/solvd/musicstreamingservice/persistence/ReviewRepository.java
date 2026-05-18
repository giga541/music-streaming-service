package com.solvd.musicstreamingservice.persistence;

import com.solvd.musicstreamingservice.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    Review create(Review review);

    Optional<Review> findById(Long id);

    List<Review> findAll();

    Review update(Review review);

    void delete(Long id);

    List<Review> findBySongId(Long songId);

    List<Review> findByRatingGreaterThan(int rating);
}