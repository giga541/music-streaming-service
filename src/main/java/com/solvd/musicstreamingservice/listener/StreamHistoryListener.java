package com.solvd.musicstreamingservice.listener;

import com.solvd.musicstreamingservice.model.Song;
import com.solvd.musicstreamingservice.model.StreamHistory;
import com.solvd.musicstreamingservice.persistence.impl.UserRepositoryImpl;

import java.time.LocalDateTime;

public class StreamHistoryListener implements StreamEventListener {

    @Override
    public void onSongPlayed(Song song) {
        StreamHistory history = new StreamHistory();
        history.setSongId(song.getId());
        history.setPlayedAt(LocalDateTime.now());
        history.setListenedSeconds(song.getDurationSeconds());
        System.out.println("Stream recorded for song: " + song.getTitle());
    }
}