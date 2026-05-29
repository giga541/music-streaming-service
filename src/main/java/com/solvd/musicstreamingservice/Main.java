package com.solvd.musicstreamingservice;

import com.solvd.musicstreamingservice.decorator.LoggingUserRepository;
import com.solvd.musicstreamingservice.facade.MusicServiceFacade;
import com.solvd.musicstreamingservice.factory.MyBatisRepositoryFactory;
import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.listener.StreamHistoryListener;
import com.solvd.musicstreamingservice.listener.WelcomeEmailListener;
import com.solvd.musicstreamingservice.model.*;
import com.solvd.musicstreamingservice.persistence.UserRepository;
import com.solvd.musicstreamingservice.persistence.impl.UserMapperImpl;
import com.solvd.musicstreamingservice.strategy.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Abstract Factory — switch between JDBC and MyBatis in one line
        RepositoryFactory factory = new MyBatisRepositoryFactory();

        // Facade — single entry point for the whole app
        MusicServiceFacade facade = new MusicServiceFacade(factory);

        // Listener — register event listeners
        facade.addUserListener(new WelcomeEmailListener());
        facade.addStreamListener(new StreamHistoryListener());

        // Builder — build User object step by step
        User user = new User.Builder()
                .username("test_user")
                .email("solvdt@email.com")
                .premium(true)
                .registrationDate(LocalDate.now())
                .lastLogin(LocalDateTime.now())
                .musicServiceId(1L)
                .build();

        // Facade + Listener — registers user and fires WelcomeEmailListener
        user = facade.registerUser(user);
        System.out.println("Registered user id: " + user.getId());

        // Facade + Listener — plays song and fires StreamHistoryListener
        facade.playSong(1L);

        // Decorator — wrap repository with logging
        UserRepository loggingRepo = new LoggingUserRepository(new UserMapperImpl());
        loggingRepo.findAll().forEach(u -> System.out.println("User: " + u.getUsername()));

        // Strategy — search songs by different criteria
        List<Song> allSongs = facade.findAllSongsWithDetails();
        SongSearcher searcher = new SongSearcher(new SearchByTitleStrategy());
        List<Song> byTitle = searcher.search("Smooth", allSongs);
        System.out.println("Songs found by title: " + byTitle.size());

        searcher.setStrategy(new SearchByArtistStrategy());
        List<Song> byArtist = searcher.search("Sade", allSongs);
        System.out.println("Songs found by artist: " + byArtist.size());

        searcher.setStrategy(new SearchByGenreStrategy());
        List<Song> byGenre = searcher.search("Soul", allSongs);
        System.out.println("Songs found by genre: " + byGenre.size());

        // Builder — build Playlist
        Playlist playlist = new Playlist.Builder()
                .name("My Playlist")
                .open(true)
                .userId(1L)
                .build();
        System.out.println("Built playlist: " + playlist.getName());

        // Cleanup
        facade.deleteUser(user.getId());
        System.out.println("Deleted user");
    }
}