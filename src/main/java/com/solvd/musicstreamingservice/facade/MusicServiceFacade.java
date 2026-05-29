package com.solvd.musicstreamingservice.facade;

import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.listener.StreamEventListener;
import com.solvd.musicstreamingservice.listener.UserEventListener;
import com.solvd.musicstreamingservice.model.*;
import com.solvd.musicstreamingservice.service.*;
import com.solvd.musicstreamingservice.service.impl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicServiceFacade {

    private final UserService userService;
    private final SongService songService;
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final PlaylistService playlistService;
    private final ReviewService reviewService;
    private final List<StreamEventListener> streamListeners = new ArrayList<>();
    private final List<UserEventListener> userListeners = new ArrayList<>();

    public MusicServiceFacade(RepositoryFactory factory) {

        this.userService = new UserServiceImpl(factory);
        this.songService = new SongServiceImpl(factory);
        this.artistService = new ArtistServiceImpl(factory);
        this.albumService = new AlbumServiceImpl(factory);
        this.playlistService = new PlaylistServiceImpl(factory);
        this.reviewService = new ReviewServiceImpl(factory);
    }

    public void addStreamListener(StreamEventListener listener) {
        streamListeners.add(listener);
    }

    public void addUserListener(UserEventListener listener) {
        userListeners.add(listener);
    }

    public User registerUser(User user) {
        User created = userService.create(user);
        userListeners.forEach(l -> l.onUserRegistered(created));
        return created;
    }

    public void playSong(Long songId) {
        songService.findById(songId)
                .ifPresent(song -> streamListeners.forEach(l -> l.onSongPlayed(song)));
    }

    public Optional<User> findUser(Long id) {
        return userService.findById(id);
    }

    public List<Song> findAllSongsWithDetails() {
        return songService.findAllWithDetails();
    }

    public List<Artist> findAllArtists() {
        return artistService.findAll();
    }

    public List<Album> findAlbumsByArtist(Long artistId) {
        return albumService.findByArtistId(artistId);
    }

    public List<Playlist> findUserPlaylists(Long userId) {
        return playlistService.findByUserId(userId);
    }

    public List<Review> findSongReviews(Long songId) {
        return reviewService.findBySongId(songId);
    }

    public void deleteUser(Long id) {
        userService.delete(id);
    }
}