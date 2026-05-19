package com.solvd.musicstreamingservice.service.impl;

import com.solvd.musicstreamingservice.model.Playlist;
import com.solvd.musicstreamingservice.persistence.PlaylistRepository;
import com.solvd.musicstreamingservice.persistence.impl.PlaylistRepositoryImpl;
import com.solvd.musicstreamingservice.service.PlaylistService;

import java.util.List;
import java.util.Optional;

public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl() {
        this.playlistRepository = new PlaylistRepositoryImpl();
    }

    @Override
    public Playlist create(Playlist playlist) {
        if (playlist.getName() == null || playlist.getName().isBlank()) {
            throw new IllegalArgumentException("Playlist name cannot be empty.");
        }
        return playlistRepository.create(playlist);
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist update(Playlist playlist) {
        return playlistRepository.update(playlist);
    }

    @Override
    public void delete(Long id) {
        playlistRepository.delete(id);
    }

    @Override
    public List<Playlist> findByUserId(Long userId) {
        return playlistRepository.findByUserId(userId);
    }

    @Override
    public List<Playlist> findAllPublic() {
        return playlistRepository.findAllPublic();
    }
}