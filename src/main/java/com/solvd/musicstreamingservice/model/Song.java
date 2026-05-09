package com.solvd.musicstreamingservice.model;

import java.time.LocalDate;
import java.util.List;

public class Song {

    private Long id;
    private String title;
    private double durationSeconds;
    private LocalDate releaseDate;
    private Genre genre;
    private List<Review> reviews;
    private List<StreamHistory> streamHistories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(double durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<StreamHistory> getStreamHistories() {
        return streamHistories;
    }

    public void setStreamHistories(List<StreamHistory> streamHistories) {
        this.streamHistories = streamHistories;
    }

}