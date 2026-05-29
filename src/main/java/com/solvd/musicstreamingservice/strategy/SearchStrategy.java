package com.solvd.musicstreamingservice.strategy;

import com.solvd.musicstreamingservice.model.Song;

import java.util.List;

public interface SearchStrategy {

    List<Song> search(String query, List<Song> songs);
}