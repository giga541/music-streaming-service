package com.solvd.musicstreamingservice.model;

import java.time.LocalDateTime;

public class StreamHistory {

    private Long id;
    private LocalDateTime playedAt;
    private double listenedSeconds;
    private Long songId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }

    public double getListenedSeconds() {
        return listenedSeconds;
    }

    public void setListenedSeconds(double listenedSeconds) {
        this.listenedSeconds = listenedSeconds;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}