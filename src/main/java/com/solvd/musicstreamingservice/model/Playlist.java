package com.solvd.musicstreamingservice.model;

import java.util.List;

public class Playlist {

    private Long id;
    private String name;
    private boolean open;
    private Long userId;
    private List<Song> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public static class Builder {

        private String name;
        private boolean open;
        private Long userId;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder open(boolean open) {
            this.open = open;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Playlist build() {
            Playlist playlist = new Playlist();
            playlist.setName(name);
            playlist.setOpen(open);
            playlist.setUserId(userId);
            return playlist;
        }
    }
}