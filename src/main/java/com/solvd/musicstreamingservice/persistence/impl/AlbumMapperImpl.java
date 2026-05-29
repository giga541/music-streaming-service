package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Album;
import com.solvd.musicstreamingservice.persistence.AlbumRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AlbumMapperImpl implements AlbumRepository {

    @Override
    public void create(Album album) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(AlbumRepository.class).create(album);
        }
    }

    @Override
    public void update(Album album) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(AlbumRepository.class).update(album);
        }
    }

    @Override
    public Optional<Album> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(AlbumRepository.class).findById(id);
        }
    }

    @Override
    public List<Album> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(AlbumRepository.class).findAll();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(AlbumRepository.class).delete(id);
        }
    }

    @Override
    public List<Album> findByArtistId(Long artistId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(AlbumRepository.class).findByArtistId(artistId);
        }
    }
}