package com.solvd.musicstreamingservice.factory;

import com.solvd.musicstreamingservice.persistence.*;
import com.solvd.musicstreamingservice.persistence.impl.*;

public class MyBatisRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository createUserRepository() {
        return new UserMapperImpl();
    }

    @Override
    public ArtistRepository createArtistRepository() {
        return new ArtistMapperImpl();
    }

    @Override
    public AlbumRepository createAlbumRepository() {
        return new AlbumMapperImpl();
    }

    @Override
    public SongRepository createSongRepository() {
        return new SongMapperImpl();
    }

    @Override
    public PlaylistRepository createPlaylistRepository() {
        return new PlaylistMapperImpl();
    }

    @Override
    public ReviewRepository createReviewRepository() {
        return new ReviewMapperImpl();
    }
}