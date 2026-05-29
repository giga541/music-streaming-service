package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Song;
import com.solvd.musicstreamingservice.persistence.SongRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class SongMapperImpl implements SongRepository {

    @Override
    public void create(Song song) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(SongRepository.class).create(song);
        }
    }

    @Override
    public void update(Song song) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(SongRepository.class).update(song);
        }
    }

    @Override
    public Optional<Song> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(SongRepository.class).findById(id);
        }
    }

    @Override
    public List<Song> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(SongRepository.class).findAll();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(SongRepository.class).delete(id);
        }
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(SongRepository.class).findByAlbumId(albumId);
        }
    }

    @Override
    public List<Song> findByGenreId(Long genreId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(SongRepository.class).findByGenreId(genreId);
        }
    }

    @Override
    public List<Song> findAllWithDetails() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(SongRepository.class).findAllWithDetails();
        }
    }
}