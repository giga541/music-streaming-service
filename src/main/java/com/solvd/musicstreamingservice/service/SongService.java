package com.solvd.musicstreamingservice.service;

import com.solvd.musicstreamingservice.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    Song create(Song song);

    Optional<Song> findById(Long id);

    List<Song> findAll();

    Song update(Song song);

    void delete(Long id);

    List<Song> findByAlbumId(Long albumId);

    List<Song> findByGenreId(Long genreId);

    List<Song> findAllWithDetails();
}