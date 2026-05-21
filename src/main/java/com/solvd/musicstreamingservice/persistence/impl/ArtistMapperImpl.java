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
            session.insert("com.solvd.musicstreamingservice.persistence.ArtistRepository.create", artist);
        }
        return artist;
    }

    @Override
    public Optional<Artist> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.selectOne(
                    "com.solvd.musicstreamingservice.persistence.ArtistRepository.findById", id));
        }
    }

    @Override
    public List<Artist> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList("com.solvd.musicstreamingservice.persistence.ArtistRepository.findAll");
        }
    }

    @Override
    public Artist update(Artist artist) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.update("com.solvd.musicstreamingservice.persistence.ArtistRepository.update", artist);
        }
        return artist;
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession(true)) {
            session.delete("com.solvd.musicstreamingservice.persistence.ArtistRepository.delete", id);
        }
    }

    @Override
    public List<Artist> findByMusicServiceId(Long musicServiceId) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.ArtistRepository.findByMusicServiceId", musicServiceId);
        }
    }

    @Override
    public List<Artist> findByCountry(String country) {
        try (SqlSession session = MyBatisSessionHolder.getSessionFactory().openSession()) {
            return session.selectList(
                    "com.solvd.musicstreamingservice.persistence.ArtistRepository.findByCountry", country);
        }
    }
}