package com.solvd.musicstreamingservice.model;

import java.util.List;

public class Playlist {

    private String name;
    private boolean isPublic;
    private List<Song> songs;

    public Playlist(String name, boolean isPublic, List<Song> songs) {
        this.name = name;
        this.isPublic = isPublic;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
