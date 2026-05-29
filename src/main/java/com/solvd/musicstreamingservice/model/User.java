package com.solvd.musicstreamingservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class User {

    private Long id;
    private String username;
    private String email;
    private boolean premium;
    private LocalDate registrationDate;
    private LocalDateTime lastLogin;
    private Long musicServiceId;
    private List<Playlist> playlists;
    private List<Subscription> subscriptions;
    private List<PaymentMethod> paymentMethods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean premium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getMusicServiceId() {
        return musicServiceId;
    }

    public void setMusicServiceId(Long musicServiceId) {
        this.musicServiceId = musicServiceId;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
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