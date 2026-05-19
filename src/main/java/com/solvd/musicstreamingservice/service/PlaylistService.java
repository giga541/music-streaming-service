package com.solvd.musicstreamingservice.service;

import com.solvd.musicstreamingservice.model.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {

    Playlist create(Playlist playlist);

    Optional<Playlist> findById(Long id);

    List<Playlist> findAll();

    Playlist update(Playlist playlist);

    void delete(Long id);

    List<Playlist> findByUserId(Long userId);

    List<Playlist> findAllPublic();
}