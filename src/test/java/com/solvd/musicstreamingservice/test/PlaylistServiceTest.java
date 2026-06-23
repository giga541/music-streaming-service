package com.solvd.musicstreamingservice.test;

import com.solvd.musicstreamingservice.factory.MyBatisRepositoryFactory;
import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.model.Playlist;
import com.solvd.musicstreamingservice.service.PlaylistService;
import com.solvd.musicstreamingservice.service.impl.PlaylistServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Listeners(com.solvd.musicstreamingservice.listener.TestNGListener.class)
public class PlaylistServiceTest {

    private PlaylistService playlistService;
    private Playlist testPlaylist;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Starting Playlist Service Test Suite");
    }

    @BeforeClass
    public void beforeClass() {
        RepositoryFactory factory = new MyBatisRepositoryFactory();
        playlistService = new PlaylistServiceImpl(factory);
        System.out.println("PlaylistService initialized");
    }

    @BeforeMethod
    public void beforeMethod() {
        testPlaylist = new Playlist.Builder()
                .name("Test Playlist")
                .open(true)
                .userId(1L)
                .build();
        System.out.println("Test playlist created");
    }

    @Test
    public void testCreatePlaylist() {
        playlistService.create(testPlaylist);
        Assert.assertNotNull(testPlaylist.getId(), "Playlist id should not be null after creation");
    }

    @Test
    public void testFindPlaylistById() {
        playlistService.create(testPlaylist);
        Playlist found = playlistService.findById(testPlaylist.getId()).orElse(null);
        Assert.assertNotNull(found, "Playlist should be found by id");
    }

    @Test
    public void testFindAllPublicPlaylists() {
        playlistService.create(testPlaylist);
        List<Playlist> publicPlaylists = playlistService.findAllPublic();
        Assert.assertFalse(publicPlaylists.isEmpty(), "Public playlists should not be empty");
    }

    @Test
    public void testFindPlaylistsByUserId() {
        playlistService.create(testPlaylist);
        List<Playlist> playlists = playlistService.findByUserId(1L);
        Assert.assertFalse(playlists.isEmpty(), "Should find playlists by user id");
    }

    @Test
    public void testUpdatePlaylist() {
        playlistService.create(testPlaylist);
        testPlaylist.setName("Updated Playlist");
        Playlist updated = playlistService.update(testPlaylist);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updated.getName(), "Updated Playlist", "Name should be updated");
        softAssert.assertTrue(updated.isOpen(), "Playlist should still be public");
        softAssert.assertAll();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreatePlaylistWithEmptyName() {
        testPlaylist.setName("");
        playlistService.create(testPlaylist);
    }

    @AfterMethod
    public void afterMethod() {
        if (testPlaylist.getId() != null) {
            playlistService.delete(testPlaylist.getId());
        }
        System.out.println("Test playlist cleaned up");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("PlaylistService tests completed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Playlist Service Test Suite finished");
    }
}