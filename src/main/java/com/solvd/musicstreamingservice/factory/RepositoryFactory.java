package com.solvd.musicstreamingservice.factory;

import com.solvd.musicstreamingservice.persistence.*;

public interface RepositoryFactory {

    UserRepository createUserRepository();

    ArtistRepository createArtistRepository();

    AlbumRepository createAlbumRepository();

    SongRepository createSongRepository();

    PlaylistRepository createPlaylistRepository();

    ReviewRepository createReviewRepository();
}