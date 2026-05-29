package com.solvd.musicstreamingservice.persistence;

import com.solvd.musicstreamingservice.model.Artist;
import java.util.List;
import java.util.Optional;

public interface ArtistRepository {

    void create(Artist artist);

    Optional<Artist> findById(Long id);

    List<Artist> findAll();

    void update(Artist artist);

    void delete(Long id);

    List<Artist> findByMusicServiceId(Long musicServiceId);

    List<Artist> findByCountry(String country);
}