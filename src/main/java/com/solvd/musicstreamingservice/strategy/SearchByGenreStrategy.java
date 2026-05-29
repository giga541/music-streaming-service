package com.solvd.musicstreamingservice.strategy;

import com.solvd.musicstreamingservice.model.Song;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByGenreStrategy implements SearchStrategy {

    @Override
    public List<Song> search(String query, List<Song> songs) {
        return songs.stream()
                .filter(song -> song.getGenre() != null &&
                        song.getGenre().getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}