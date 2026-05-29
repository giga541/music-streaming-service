package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Review;
import com.solvd.musicstreamingservice.persistence.ReviewRepository;
import com.solvd.musicstreamingservice.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewRepositoryImpl implements ReviewRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY =
            "INSERT INTO reviews (rating, comment, created_at, song_id) VALUES (?, ?, ?, ?)";

    private static final String FIND_BY_ID_QUERY =
            "SELECT * FROM reviews WHERE id = ?";

    private static final String FIND_ALL_QUERY =
            "SELECT * FROM reviews";

    private static final String UPDATE_QUERY =
            "UPDATE reviews SET rating = ?, comment = ?, created_at = ?, song_id = ? WHERE id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM reviews WHERE id = ?";

    private static final String FIND_BY_SONG_ID_QUERY =
            "SELECT * FROM reviews WHERE song_id = ?";

    private static final String FIND_BY_RATING_GREATER_THAN_QUERY =
            "SELECT * FROM reviews WHERE rating > ?";

    @Override
    public void create(Review review) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, review.getRating());
            preparedStatement.setString(2, review.getComment());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(review.getCreatedAt()));
            preparedStatement.setLong(4, review.getSongId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                review.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create review.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Review> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapReview(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find review by id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            reviews = mapReviews(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all reviews.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public void update(Review review) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, review.getRating());
            preparedStatement.setString(2, review.getComment());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(review.getCreatedAt()));
            preparedStatement.setLong(4, review.getSongId());
            preparedStatement.setLong(5, review.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update review.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete review.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Review> findBySongId(Long songId) {
        List<Review> reviews;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SONG_ID_QUERY)) {
            preparedStatement.setLong(1, songId);
            ResultSet resultSet = preparedStatement.executeQuery();
            reviews = mapReviews(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find reviews by song id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public List<Review> findByRatingGreaterThan(int rating) {
        List<Review> reviews;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_RATING_GREATER_THAN_QUERY)) {
            preparedStatement.setInt(1, rating);
            ResultSet resultSet = preparedStatement.executeQuery();
            reviews = mapReviews(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find reviews by rating.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return reviews;
    }

    private List<Review> mapReviews(ResultSet resultSet) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        while (resultSet.next()) {
            reviews.add(mapReview(resultSet));
        }
        return reviews;
    }

    private Review mapReview(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getLong("id"));
        review.setRating(resultSet.getInt("rating"));
        review.setComment(resultSet.getString("comment"));
        if (resultSet.getTimestamp("created_at") != null) {
            review.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        }
        review.setSongId(resultSet.getLong("song_id"));
        return review;
    }
}