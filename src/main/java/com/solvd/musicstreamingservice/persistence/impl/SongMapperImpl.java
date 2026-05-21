package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Song;
import com.solvd.musicstreamingservice.persistence.SongRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class SongMapperImpl implements SongRepository {

    @Override
    public Song create(Song song) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.insert("com.solvd.musicstreamingservice.persistence.SongRepository.create", song);
        }
        return song;
    }

    @Override
    public Optional<Song> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.selectOne(
                    "com.solvd.musicstreamingservice.persistence.SongRepository.findById", id));
        }
    }

    @Override
    public List<Song> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList("com.solvd.musicstreamingservice.persistence.SongRepository.findAll");
        }
    }

    @Override
    public Song update(Song song) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.update("com.solvd.musicstreamingservice.persistence.SongRepository.update", song);
        }
        return song;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.delete("com.solvd.musicstreamingservice.persistence.SongRepository.delete", id);
        }
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.SongRepository.findByAlbumId", albumId);
        }
    }

    @Override
    public List<Song> findByGenreId(Long genreId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.SongRepository.findByGenreId", genreId);
        }
    }

    @Override
    public List<Song> findAllWithDetails() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.SongRepository.findAllWithDetails");
        }
    }
}