package com.solvd.musicstreamingservice.persistence;

import com.solvd.musicstreamingservice.model.Playlist;
import java.util.List;
import java.util.Optional;

public interface PlaylistRepository {

    void create(Playlist playlist);

    Optional<Playlist> findById(Long id);

    List<Playlist> findAll();

    void update(Playlist playlist);

    void delete(Long id);

    List<Playlist> findByUserId(Long userId);

    List<Playlist> findAllPublic();
}