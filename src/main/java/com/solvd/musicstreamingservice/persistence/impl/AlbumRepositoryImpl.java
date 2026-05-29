package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Album;
import com.solvd.musicstreamingservice.persistence.AlbumRepository;
import com.solvd.musicstreamingservice.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumRepositoryImpl implements AlbumRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY =
            "INSERT INTO albums (title, release_date, artist_id) VALUES (?, ?, ?)";

    private static final String FIND_BY_ID_QUERY =
            "SELECT * FROM albums WHERE id = ?";

    private static final String FIND_ALL_QUERY =
            "SELECT * FROM albums";

    private static final String UPDATE_QUERY =
            "UPDATE albums SET title = ?, release_date = ?, artist_id = ? WHERE id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM albums WHERE id = ?";

    private static final String FIND_BY_ARTIST_ID_QUERY =
            "SELECT * FROM albums WHERE artist_id = ?";

    @Override
    public void create(Album album) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, album.getTitle());
            preparedStatement.setDate(2, Date.valueOf(album.getReleaseDate()));
            preparedStatement.setLong(3, album.getArtistId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                album.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create album.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Album> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapAlbum(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find album by id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Album> findAll() {
        List<Album> albums;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            albums = mapAlbums(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all albums.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return albums;
    }

    @Override
    public void update(Album album) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, album.getTitle());
            preparedStatement.setDate(2, Date.valueOf(album.getReleaseDate()));
            preparedStatement.setLong(3, album.getArtistId());
            preparedStatement.setLong(4, album.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update album.", e);
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
            throw new RuntimeException("Unable to delete album.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Album> findByArtistId(Long artistId) {
        List<Album> albums;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ARTIST_ID_QUERY)) {
            preparedStatement.setLong(1, artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            albums = mapAlbums(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find albums by artist id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return albums;
    }

    private List<Album> mapAlbums(ResultSet resultSet) throws SQLException {
        List<Album> albums = new ArrayList<>();
        while (resultSet.next()) {
            albums.add(mapAlbum(resultSet));
        }
        return albums;
    }

    private Album mapAlbum(ResultSet resultSet) throws SQLException {
        Album album = new Album();
        album.setId(resultSet.getLong("id"));
        album.setTitle(resultSet.getString("title"));
        if (resultSet.getDate("release_date") != null) {
            album.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        }
        album.setArtistId(resultSet.getLong("artist_id"));
        return album;
    }
}