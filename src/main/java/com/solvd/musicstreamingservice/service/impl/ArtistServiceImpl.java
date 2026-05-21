package com.solvd.musicstreamingservice.service.impl;

import com.solvd.musicstreamingservice.model.Artist;
import com.solvd.musicstreamingservice.persistence.ArtistRepository;
import com.solvd.musicstreamingservice.persistence.impl.ArtistMapperImpl;
import com.solvd.musicstreamingservice.persistence.impl.ArtistRepositoryImpl;
import com.solvd.musicstreamingservice.service.ArtistService;

import java.util.List;
import java.util.Optional;

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl() {
        this.artistRepository = new ArtistMapperImpl();
    }

    @Override
    public Artist create(Artist artist) {
        if (artist.getName() == null || artist.getName().isBlank()) {
            throw new IllegalArgumentException("Artist name cannot be empty.");
        }
        return artistRepository.create(artist);
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist update(Artist artist) {
        return artistRepository.update(artist);
    }

    @Override
    public void delete(Long id) {
        artistRepository.delete(id);
    }

    @Override
    public List<Artist> findByMusicServiceId(Long musicServiceId) {
        return artistRepository.findByMusicServiceId(musicServiceId);
    }

    @Override
    public List<Artist> findByCountry(String country) {
        return artistRepository.findByCountry(country);
    }
}