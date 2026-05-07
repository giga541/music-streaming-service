package com.solvd.musicstreamingservice.model;

import java.time.LocalDate;
import java.util.List;

public class Album {

    private String title;
    private LocalDate releaseDate;
    private Artist artist;
    private List<Song> songs;
}
