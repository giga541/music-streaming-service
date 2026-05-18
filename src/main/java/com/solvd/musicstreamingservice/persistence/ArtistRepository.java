package com.solvd.musicstreamingservice.persistence;

import com.solvd.musicstreamingservice.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository {

    Artist create(Artist artist);

    Optional<Artist> findById(Long id);

    List<Artist> findAll();

    Artist update(Artist artist);

    void delete(Long id);

    List<Artist> findByMusicServiceId(Long musicServiceId);

    List<Artist> findByCountry(String country);
}