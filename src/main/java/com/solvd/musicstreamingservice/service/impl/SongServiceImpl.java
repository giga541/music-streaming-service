package com.solvd.musicstreamingservice.service.impl;

import com.solvd.musicstreamingservice.model.Song;
import com.solvd.musicstreamingservice.persistence.SongRepository;
import com.solvd.musicstreamingservice.persistence.impl.SongMapperImpl;
import com.solvd.musicstreamingservice.persistence.impl.SongRepositoryImpl;
import com.solvd.musicstreamingservice.service.SongService;

import java.util.List;
import java.util.Optional;

public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl() {
        this.songRepository = new SongMapperImpl();
    }

    @Override
    public Song create(Song song) {
        if (song.getTitle() == null || song.getTitle().isBlank()) {
            throw new IllegalArgumentException("Song title cannot be empty.");
        }
        return songRepository.create(song);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song update(Song song) {
        return songRepository.update(song);
    }

    @Override
    public void delete(Long id) {
        songRepository.delete(id);
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        return songRepository.findByAlbumId(albumId);
    }

    @Override
    public List<Song> findByGenreId(Long genreId) {
        return songRepository.findByGenreId(genreId);
    }

    @Override
    public List<Song> findAllWithDetails() {
        return songRepository.findAllWithDetails();
    }
}