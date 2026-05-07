package com.solvd.musicstreamingservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class User {

    private String username;
    private String email;
    private boolean isPremium;
    private LocalDate registrationDate;
    private LocalDateTime lastLogin;
    private List<Playlist> playlists;
    private List<StreamHistory> streamHistory;

    public User(String username, String email, boolean isPremium,
                LocalDate registrationDate, LocalDateTime lastLogin,
                List<Playlist> playlists, List<StreamHistory> streamHistory) {
        this.username = username;
        this.email = email;
        this.isPremium = isPremium;
        this.registrationDate = registrationDate;
        this.lastLogin = lastLogin;
        this.playlists = playlists;
        this.streamHistory = streamHistory;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public List<StreamHistory> getStreamHistory() {
        return streamHistory;
    }
}
