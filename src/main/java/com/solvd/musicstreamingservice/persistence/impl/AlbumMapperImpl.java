package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Album;
import com.solvd.musicstreamingservice.persistence.AlbumRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AlbumMapperImpl implements AlbumRepository {

    @Override
    public Album create(Album album) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.insert("com.solvd.musicstreamingservice.persistence.AlbumRepository.create", album);
        }
        return album;
    }

    @Override
    public Optional<Album> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.selectOne(
                    "com.solvd.musicstreamingservice.persistence.AlbumRepository.findById", id));
        }
    }

    @Override
    public List<Album> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList("com.solvd.musicstreamingservice.persistence.AlbumRepository.findAll");
        }
    }

    @Override
    public Album update(Album album) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.update("com.solvd.musicstreamingservice.persistence.AlbumRepository.update", album);
        }
        return album;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.delete("com.solvd.musicstreamingservice.persistence.AlbumRepository.delete", id);
        }
    }

    @Override
    public List<Album> findByArtistId(Long artistId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.AlbumRepository.findByArtistId", artistId);
        }
    }
}