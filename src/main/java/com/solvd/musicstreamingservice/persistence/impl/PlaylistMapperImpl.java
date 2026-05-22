package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Playlist;
import com.solvd.musicstreamingservice.persistence.PlaylistRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PlaylistMapperImpl implements PlaylistRepository {

    @Override
    public Playlist create(Playlist playlist) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(PlaylistRepository.class).create(playlist);
        }
        return playlist;
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(PlaylistRepository.class).findById(id);
        }
    }

    @Override
    public List<Playlist> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(PlaylistRepository.class).findAll();
        }
    }

    @Override
    public Playlist update(Playlist playlist) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(PlaylistRepository.class).update(playlist);
        }
        return playlist;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(PlaylistRepository.class).delete(id);
        }
    }

    @Override
    public List<Playlist> findByUserId(Long userId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(PlaylistRepository.class).findByUserId(userId);
        }
    }

    @Override
    public List<Playlist> findAllPublic() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(PlaylistRepository.class).findAllPublic();
        }
    }
}