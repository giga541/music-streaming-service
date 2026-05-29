package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Artist;
import com.solvd.musicstreamingservice.persistence.ArtistRepository;
import com.solvd.musicstreamingservice.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistRepositoryImpl implements ArtistRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY =
            "INSERT INTO artists (name, country, music_service_id) VALUES (?, ?, ?)";

    private static final String FIND_BY_ID_QUERY =
            "SELECT * FROM artists WHERE id = ?";

    private static final String FIND_ALL_QUERY =
            "SELECT * FROM artists";

    private static final String UPDATE_QUERY =
            "UPDATE artists SET name = ?, country = ?, music_service_id = ? WHERE id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM artists WHERE id = ?";

    private static final String FIND_BY_MUSIC_SERVICE_ID_QUERY =
            "SELECT * FROM artists WHERE music_service_id = ?";

    private static final String FIND_BY_COUNTRY_QUERY =
            "SELECT * FROM artists WHERE country = ?";

    @Override
    public void create(Artist artist) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, artist.getCountry());
            preparedStatement.setLong(3, artist.getMusicServiceId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                artist.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create artist.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Artist> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapArtist(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find artist by id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Artist> findAll() {
        List<Artist> artists;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            artists = mapArtists(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all artists.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return artists;
    }

    @Override
    public void update(Artist artist) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, artist.getCountry());
            preparedStatement.setLong(3, artist.getMusicServiceId());
            preparedStatement.setLong(4, artist.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update artist.", e);
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
            throw new RuntimeException("Unable to delete artist.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Artist> findByMusicServiceId(Long musicServiceId) {
        List<Artist> artists;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_MUSIC_SERVICE_ID_QUERY)) {
            preparedStatement.setLong(1, musicServiceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            artists = mapArtists(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find artists by music service id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return artists;
    }

    @Override
    public List<Artist> findByCountry(String country) {
        List<Artist> artists;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_COUNTRY_QUERY)) {
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            artists = mapArtists(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find artists by country.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return artists;
    }

    private List<Artist> mapArtists(ResultSet resultSet) throws SQLException {
        List<Artist> artists = new ArrayList<>();
        while (resultSet.next()) {
            artists.add(mapArtist(resultSet));
        }
        return artists;
    }

    private Artist mapArtist(ResultSet resultSet) throws SQLException {
        Artist artist = new Artist();
        artist.setId(resultSet.getLong("id"));
        artist.setName(resultSet.getString("name"));
        artist.setCountry(resultSet.getString("country"));
        artist.setMusicServiceId(resultSet.getLong("music_service_id"));
        return artist;
    }
}