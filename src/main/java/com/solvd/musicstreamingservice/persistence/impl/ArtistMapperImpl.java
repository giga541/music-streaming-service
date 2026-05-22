package com.solvd.musicstreamingservice.persistence.impl;

import com.solvd.musicstreamingservice.model.Artist;
import com.solvd.musicstreamingservice.persistence.ArtistRepository;
import com.solvd.musicstreamingservice.util.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class ArtistMapperImpl implements ArtistRepository {

    @Override
    public Artist create(Artist artist) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(ArtistRepository.class).create(artist);
        }
        return artist;
    }

    @Override
    public Optional<Artist> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ArtistRepository.class).findById(id);
        }
    }

    @Override
    public List<Artist> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ArtistRepository.class).findAll();
        }
    }

    @Override
    public Artist update(Artist artist) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(ArtistRepository.class).update(artist);
        }
        return artist;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.getMapper(ArtistRepository.class).delete(id);
        }
    }

    @Override
    public List<Artist> findByMusicServiceId(Long musicServiceId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ArtistRepository.class).findByMusicServiceId(musicServiceId);
        }
    }

    @Override
    public List<Artist> findByCountry(String country) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.getMapper(ArtistRepository.class).findByCountry(country);
        }
    }
}