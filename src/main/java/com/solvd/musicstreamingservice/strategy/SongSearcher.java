package com.solvd.musicstreamingservice.strategy;

import com.solvd.musicstreamingservice.model.Song;

import java.util.List;

public class SongSearcher {

    private SearchStrategy strategy;

    public SongSearcher(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Song> search(String query, List<Song> songs) {
        return strategy.search(query, songs);
    }
}