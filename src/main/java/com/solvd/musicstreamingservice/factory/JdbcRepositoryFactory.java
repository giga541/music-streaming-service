package com.solvd.musicstreamingservice.factory;

import com.solvd.musicstreamingservice.persistence.*;
import com.solvd.musicstreamingservice.persistence.impl.*;

public class JdbcRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository createUserRepository() {
        return new UserRepositoryImpl();
    }

    @Override
    public ArtistRepository createArtistRepository() {
        return new ArtistRepositoryImpl();
    }

    @Override
    public AlbumRepository createAlbumRepository() {
        return new AlbumRepositoryImpl();
    }

    @Override
    public SongRepository createSongRepository() {
        return new SongRepositoryImpl();
    }

    @Override
    public PlaylistRepository createPlaylistRepository() {
        return new PlaylistRepositoryImpl();
    }

    @Override
    public ReviewRepository createReviewRepository() {
        return new ReviewRepositoryImpl();
    }
}