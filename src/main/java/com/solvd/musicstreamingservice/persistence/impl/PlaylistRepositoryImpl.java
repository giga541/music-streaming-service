package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Playlist;
import com.solvd.musicstreamingservice.persistence.PlaylistRepository;
import com.solvd.musicstreamingservice.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaylistRepositoryImpl implements PlaylistRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY =
            "INSERT INTO playlists (name, open, user_id) VALUES (?, ?, ?)";

    private static final String FIND_BY_ID_QUERY =
            "SELECT * FROM playlists WHERE id = ?";

    private static final String FIND_ALL_QUERY =
            "SELECT * FROM playlists";

    private static final String UPDATE_QUERY =
            "UPDATE playlists SET name = ?, open = ?, user_id = ? WHERE id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM playlists WHERE id = ?";

    private static final String FIND_BY_USER_ID_QUERY =
            "SELECT * FROM playlists WHERE user_id = ?";

    private static final String FIND_ALL_PUBLIC_QUERY =
            "SELECT * FROM playlists WHERE open = 1";

    @Override
    public void create(Playlist playlist) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setBoolean(2, playlist.isOpen());
            preparedStatement.setLong(3, playlist.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                playlist.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create playlist.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapPlaylist(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find playlist by id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Playlist> findAll() {
        List<Playlist> playlists;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            playlists = mapPlaylists(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all playlists.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return playlists;
    }

    @Override
    public void update(Playlist playlist) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setBoolean(2, playlist.isOpen());
            preparedStatement.setLong(3, playlist.getUserId());
            preparedStatement.setLong(4, playlist.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update playlist.", e);
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
            throw new RuntimeException("Unable to delete playlist.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Playlist> findByUserId(Long userId) {
        List<Playlist> playlists;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID_QUERY)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            playlists = mapPlaylists(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find playlists by user id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return playlists;
    }

    @Override
    public List<Playlist> findAllPublic() {
        List<Playlist> playlists;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PUBLIC_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            playlists = mapPlaylists(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find public playlists.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return playlists;
    }

    private List<Playlist> mapPlaylists(ResultSet resultSet) throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            playlists.add(mapPlaylist(resultSet));
        }
        return playlists;
    }

    private Playlist mapPlaylist(ResultSet resultSet) throws SQLException {
        Playlist playlist = new Playlist();
        playlist.setId(resultSet.getLong("id"));
        playlist.setName(resultSet.getString("name"));
        playlist.setOpen(resultSet.getBoolean("open"));
        playlist.setUserId(resultSet.getLong("user_id"));
        return playlist;
    }
}