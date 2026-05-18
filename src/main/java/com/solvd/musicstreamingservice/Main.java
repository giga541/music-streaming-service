package com.solvd.musicstreamingservice;

import com.solvd.musicstreamingservice.model.*;
import com.solvd.musicstreamingservice.persistence.impl.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        SongRepositoryImpl songRepository = new SongRepositoryImpl();
        ArtistRepositoryImpl artistRepository = new ArtistRepositoryImpl();
        AlbumRepositoryImpl albumRepository = new AlbumRepositoryImpl();
        PlaylistRepositoryImpl playlistRepository = new PlaylistRepositoryImpl();
        ReviewRepositoryImpl reviewRepository = new ReviewRepositoryImpl();

        // CREATE
        User user = new User();
        user.setUsername("test_user");
        user.setEmail("test@email.com");
        user.setPremium(true);
        user.setRegistrationDate(LocalDate.now());
        user.setLastLogin(LocalDateTime.now());
        user.setMusicServiceId(1L);
        user = userRepository.create(user);
        System.out.println("Created user id: " + user.getId());

        // READ by id
        userRepository.findById(user.getId())
                .ifPresent(u -> System.out.println("Found user: " + u.getUsername()));

        // UPDATE
        user.setUsername("updated_user");
        user = userRepository.update(user);
        System.out.println("Updated user: " + user.getUsername());

        // READ all premium
        List<User> premiumUsers = userRepository.findAllPremium();
        System.out.println("Premium users: " + premiumUsers.size());

        // READ songs with 5 joins
        List<Song> songsWithDetails = songRepository.findAllWithDetails();
        System.out.println("Songs with details: " + songsWithDetails.size());

        // DELETE
        userRepository.delete(user.getId());
        System.out.println("Deleted user");
    }
}