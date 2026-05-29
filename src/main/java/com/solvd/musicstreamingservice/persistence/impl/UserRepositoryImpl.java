package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Playlist;
import com.solvd.musicstreamingservice.model.Subscription;
import com.solvd.musicstreamingservice.model.User;
import com.solvd.musicstreamingservice.persistence.UserRepository;
import com.solvd.musicstreamingservice.util.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY = "INSERT INTO users (username, email, premium, registration_date, last_login, music_service_id) " + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";

    private static final String FIND_ALL_QUERY = "SELECT * FROM users";

    private static final String UPDATE_QUERY = "UPDATE users SET username = ?, email = ?, premium = ?, registration_date = ?, " + "last_login = ?, music_service_id = ? WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";

    private static final String FIND_ALL_PREMIUM_QUERY = "SELECT * FROM users WHERE premium = 1";

    private static final String FIND_ALL_WITH_DETAILS_QUERY = "SELECT users.id, users.username, users.email, users.premium, " + "users.registration_date, users.last_login, users.music_service_id, " + "playlists.id AS playlist_id, playlists.name AS playlist_name, playlists.open AS playlist_open, " + "subscriptions.id AS subscription_id, subscriptions.plan, subscriptions.price_per_month, " + "subscriptions.start_date, subscriptions.end_date " + "FROM users " + "JOIN music_services ON music_services.id = users.music_service_id " + "LEFT JOIN playlists ON playlists.user_id = users.id " + "LEFT JOIN subscriptions ON subscriptions.user_id = users.id " + "LEFT JOIN payment_methods ON payment_methods.user_id = users.id";

    @Override
    public User create(User user) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setBoolean(3, user.premium());
            preparedStatement.setDate(4, Date.valueOf(user.getRegistrationDate()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(user.getLastLogin()));
            preparedStatement.setLong(6, user.getMusicServiceId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create user.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find user by id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            users = mapUsers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all users.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public User update(User user) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setBoolean(3, user.premium());
            preparedStatement.setDate(4, Date.valueOf(user.getRegistrationDate()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(user.getLastLogin()));
            preparedStatement.setLong(6, user.getMusicServiceId());
            preparedStatement.setLong(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update user.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete user.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find user by email.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllPremium() {
        List<User> users;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PREMIUM_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            users = mapUsers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find premium users.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllWithDetails() {
        List<User> users;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_WITH_DETAILS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            users = mapUsersWithDetails(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find users with details.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return users;
    }

    private List<User> mapUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(mapUser(resultSet));
        }
        return users;
    }

    private List<User> mapUsersWithDetails(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = mapUser(resultSet);
            Playlist playlist = new Playlist();
            playlist.setId(resultSet.getLong("playlist_id"));
            playlist.setName(resultSet.getString("playlist_name"));
            playlist.setOpen(resultSet.getBoolean("playlist_open"));
            Subscription subscription = new Subscription();
            subscription.setId(resultSet.getLong("subscription_id"));
            subscription.setPlan(resultSet.getString("plan"));
            subscription.setPricePerMonth(resultSet.getDouble("price_per_month"));
            if (resultSet.getDate("start_date") != null) {
                subscription.setStartDate(resultSet.getDate("start_date").toLocalDate());
            }
            if (resultSet.getDate("end_date") != null) {
                subscription.setEndDate(resultSet.getDate("end_date").toLocalDate());
            }
            users.add(user);
        }
        return users;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPremium(resultSet.getBoolean("premium"));
        if (resultSet.getDate("registration_date") != null) {
            user.setRegistrationDate(resultSet.getDate("registration_date").toLocalDate());
        }
        if (resultSet.getTimestamp("last_login") != null) {
            user.setLastLogin(resultSet.getTimestamp("last_login").toLocalDateTime());
        }
        user.setMusicServiceId(resultSet.getLong("music_service_id"));
        return user;
    }

    public static class Builder {

        private String username;
        private String email;
        private boolean premium;
        private LocalDate registrationDate;
        private LocalDateTime lastLogin;
        private Long musicServiceId;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder premium(boolean premium) {
            this.premium = premium;
            return this;
        }

        public Builder registrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder lastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder musicServiceId(Long musicServiceId) {
            this.musicServiceId = musicServiceId;
            return this;
        }

        public User build() {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPremium(premium);
            user.setRegistrationDate(registrationDate);
            user.setLastLogin(lastLogin);
            user.setMusicServiceId(musicServiceId);
            return user;
        }
    }
}