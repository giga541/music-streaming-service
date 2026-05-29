package com.solvd.musicstreamingservice.listener;

import com.solvd.musicstreamingservice.model.Song;

public interface StreamEventListener {

    void onSongPlayed(Song song);
}