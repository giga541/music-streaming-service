package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Album;
import com.solvd.musicstreamingservice.model.Artist;
import com.solvd.musicstreamingservice.model.Genre;
import com.solvd.musicstreamingservice.model.Song;
import com.solvd.musicstreamingservice.persistence.SongRepository;
import com.solvd.musicstreamingservice.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongRepositoryImpl implements SongRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String CREATE_QUERY = "INSERT INTO songs (title, duration_seconds, release_date, album_id, genre_id) " + "VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM songs WHERE id = ?";

    private static final String FIND_ALL_QUERY = "SELECT * FROM songs";

    private static final String UPDATE_QUERY = "UPDATE songs SET title = ?, duration_seconds = ?, release_date = ?, album_id = ?, genre_id = ? WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM songs WHERE id = ?";

    private static final String FIND_BY_ALBUM_ID_QUERY = "SELECT * FROM songs WHERE album_id = ?";

    private static final String FIND_BY_GENRE_ID_QUERY = "SELECT * FROM songs WHERE genre_id = ?";

    private static final String FIND_ALL_WITH_DETAILS_QUERY = "SELECT songs.id, songs.title, songs.duration_seconds, songs.release_date, songs.album_id, songs.genre_id, " + "albums.title AS album_title, albums.release_date AS album_release_date, " + "artists.id AS artist_id, artists.name AS artist_name, artists.country AS artist_country, " + "genres.id AS genre_id_col, genres.name AS genre_name, genres.description AS genre_description, " + "music_services.id AS service_id, music_services.name AS service_name " + "FROM songs " + "JOIN albums ON albums.id = songs.album_id " + "JOIN artists ON artists.id = albums.artist_id " + "JOIN genres ON genres.id = songs.genre_id " + "JOIN music_services ON music_services.id = artists.music_service_id " + "LEFT JOIN reviews ON reviews.song_id = songs.id";

    @Override
    public Song create(Song song) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, song.getTitle());
            preparedStatement.setDouble(2, song.getDurationSeconds());
            preparedStatement.setDate(3, Date.valueOf(song.getReleaseDate()));
            preparedStatement.setLong(4, song.getAlbumId());
            preparedStatement.setLong(5, song.getGenreId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                song.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create song.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return song;
    }

    @Override
    public Optional<Song> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapSong(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find song by id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public List<Song> findAll() {
        List<Song> songs;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            songs = mapSongs(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find all songs.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return songs;
    }

    @Override
    public Song update(Song song) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, song.getTitle());
            preparedStatement.setDouble(2, song.getDurationSeconds());
            preparedStatement.setDate(3, Date.valueOf(song.getReleaseDate()));
            preparedStatement.setLong(4, song.getAlbumId());
            preparedStatement.setLong(5, song.getGenreId());
            preparedStatement.setLong(6, song.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update song.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return song;
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete song.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        List<Song> songs;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ALBUM_ID_QUERY)) {
            preparedStatement.setLong(1, albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            songs = mapSongs(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find songs by album id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return songs;
    }

    @Override
    public List<Song> findByGenreId(Long genreId) {
        List<Song> songs;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_GENRE_ID_QUERY)) {
            preparedStatement.setLong(1, genreId);
            ResultSet resultSet = preparedStatement.executeQuery();
            songs = mapSongs(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find songs by genre id.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return songs;
    }

    @Override
    public List<Song> findAllWithDetails() {
        List<Song> songs;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_WITH_DETAILS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            songs = mapSongsWithDetails(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find songs with details.", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return songs;
    }

    private List<Song> mapSongs(ResultSet resultSet) throws SQLException {
        List<Song> songs = new ArrayList<>();
        while (resultSet.next()) {
            songs.add(mapSong(resultSet));
        }
        return songs;
    }

    private List<Song> mapSongsWithDetails(ResultSet resultSet) throws SQLException {
        List<Song> songs = new ArrayList<>();
        while (resultSet.next()) {
            Song song = mapSong(resultSet);
            Album album = new Album();
            album.setId(resultSet.getLong("album_id"));
            album.setTitle(resultSet.getString("album_title"));
            if (resultSet.getDate("album_release_date") != null) {
                album.setReleaseDate(resultSet.getDate("album_release_date").toLocalDate());
            }
            Artist artist = new Artist();
            artist.setId(resultSet.getLong("artist_id"));
            artist.setName(resultSet.getString("artist_name"));
            artist.setCountry(resultSet.getString("artist_country"));
            Genre genre = new Genre();
            genre.setId(resultSet.getLong("genre_id_col"));
            genre.setName(resultSet.getString("genre_name"));
            genre.setDescription(resultSet.getString("genre_description"));
            song.setAlbum(album);
            song.setArtist(artist);
            song.setGenre(genre);
            songs.add(song);
        }
        return songs;
    }

    private Song mapSong(ResultSet resultSet) throws SQLException {
        Song song = new Song();
        song.setId(resultSet.getLong("id"));
        song.setTitle(resultSet.getString("title"));
        song.setDurationSeconds(resultSet.getDouble("duration_seconds"));
        if (resultSet.getDate("release_date") != null) {
            song.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        }
        song.setAlbumId(resultSet.getLong("album_id"));
        song.setGenreId(resultSet.getLong("genre_id"));
        return song;
    }
}