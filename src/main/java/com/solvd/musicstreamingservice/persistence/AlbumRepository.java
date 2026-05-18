package com.solvd.musicstreamingservice.persistence;

import com.solvd.musicstreamingservice.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {

    Album create(Album album);

    Optional<Album> findById(Long id);

    List<Album> findAll();

    Album update(Album album);

    void delete(Long id);

    List<Album> findByArtistId(Long artistId);
}