package com.solvd.musicstreamingservice.strategy;

import com.solvd.musicstreamingservice.model.Song;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByTitleStrategy implements SearchStrategy {

    @Override
    public List<Song> search(String query, List<Song> songs) {
        return songs.stream()
                .filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}