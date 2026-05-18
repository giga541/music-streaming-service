package com.solvd.musicstreamingservice.model;

import java.util.List;

public class Artist {

    private Long id;
    private String name;
    private String country;
    private Long musicServiceId;
    private List<Album> albums;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getMusicServiceId() {
        return musicServiceId;
    }

    public void setMusicServiceId(Long musicServiceId) {
        this.musicServiceId = musicServiceId;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}