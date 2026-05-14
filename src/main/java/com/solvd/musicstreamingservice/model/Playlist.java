package com.solvd.musicstreamingservice.model;

import java.util.List;

public class Playlist {

    private Long id;
    private String name;
    private boolean open;
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

    public boolean open() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}