package com.solvd.musicstreamingservice;

import com.solvd.musicstreamingservice.model.*;
import com.solvd.musicstreamingservice.persistence.*;
import com.solvd.musicstreamingservice.persistence.impl.*;
import com.solvd.musicstreamingservice.service.*;
import com.solvd.musicstreamingservice.service.impl.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // persistence layer
        UserRepository userRepository = new UserRepositoryImpl();
        SongRepository songRepository = new SongRepositoryImpl();
        ArtistRepository artistRepository = new ArtistRepositoryImpl();
        AlbumRepository albumRepository = new AlbumRepositoryImpl();
        PlaylistRepository playlistRepository = new PlaylistRepositoryImpl();
        ReviewRepository reviewRepository = new ReviewRepositoryImpl();

        // service layer
        UserService userService = new UserServiceImpl();
        SongService songService = new SongServiceImpl();
        ArtistService artistService = new ArtistServiceImpl();
        AlbumService albumService = new AlbumServiceImpl();
        PlaylistService playlistService = new PlaylistServiceImpl();
        ReviewService reviewService = new ReviewServiceImpl();

        // CREATE via service (has validation)
        User user = new User();
        user.setUsername("test_user");
        user.setEmail("test@email.com");
        user.setPremium(true);
        user.setRegistrationDate(LocalDate.now());
        user.setLastLogin(LocalDateTime.now());
        user.setMusicServiceId(1L);
        user = userService.create(user);
        System.out.println("Created user id: " + user.getId());

        // READ by id via repository directly
        userRepository.findById(user.getId())
                .ifPresent(u -> System.out.println("Found user: " + u.getUsername()));

        // UPDATE via service
        user.setUsername("updated_user");
        user = userService.update(user);
        System.out.println("Updated user: " + user.getUsername());

        // READ all premium via repository
        List<User> premiumUsers = userRepository.findAllPremium();
        System.out.println("Premium users: " + premiumUsers.size());

        // READ songs with 5 joins via repository
        List<Song> songsWithDetails = songRepository.findAllWithDetails();
        System.out.println("Songs with details: " + songsWithDetails.size());

        // READ all artists via service
        List<Artist> artists = artistService.findAll();
        System.out.println("Artists: " + artists.size());

        // READ albums by artist via repository
        List<Album> albums = albumRepository.findByArtistId(1L);
        System.out.println("Albums by artist 1: " + albums.size());

        // READ playlists by user via service
        List<Playlist> playlists = playlistService.findByUserId(1L);
        System.out.println("Playlists for user 1: " + playlists.size());

        // READ reviews by song via repository
        List<Review> reviews = reviewRepository.findBySongId(1L);
        System.out.println("Reviews for song 1: " + reviews.size());

        // DELETE via service
        userService.delete(user.getId());
        System.out.println("Deleted user");
    }
}