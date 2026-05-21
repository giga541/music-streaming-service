package com.solvd.musicstreamingservice.service.impl;

import com.solvd.musicstreamingservice.model.Album;
import com.solvd.musicstreamingservice.persistence.AlbumRepository;
import com.solvd.musicstreamingservice.persistence.impl.AlbumMapperImpl;
import com.solvd.musicstreamingservice.persistence.impl.AlbumRepositoryImpl;
import com.solvd.musicstreamingservice.service.AlbumService;

import java.util.List;
import java.util.Optional;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl() {
        this.albumRepository = new AlbumMapperImpl();
    }

    @Override
    public Album create(Album album) {
        if (album.getTitle() == null || album.getTitle().isBlank()) {
            throw new IllegalArgumentException("Album title cannot be empty.");
        }
        return albumRepository.create(album);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album update(Album album) {
        return albumRepository.update(album);
    }

    @Override
    public void delete(Long id) {
        albumRepository.delete(id);
    }

    @Override
    public List<Album> findByArtistId(Long artistId) {
        return albumRepository.findByArtistId(artistId);
    }
}