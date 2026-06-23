package com.solvd.musicstreamingservice.test;

import com.solvd.musicstreamingservice.factory.MyBatisRepositoryFactory;
import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.model.Album;
import com.solvd.musicstreamingservice.service.AlbumService;
import com.solvd.musicstreamingservice.service.impl.AlbumServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.util.List;

@Listeners(com.solvd.musicstreamingservice.listener.TestNGListener.class)
public class AlbumServiceTest {

    private AlbumService albumService;
    private Album testAlbum;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Starting Album Service Test Suite");
    }

    @BeforeClass
    public void beforeClass() {
        RepositoryFactory factory = new MyBatisRepositoryFactory();
        albumService = new AlbumServiceImpl(factory);
        System.out.println("AlbumService initialized");
    }

    @BeforeMethod
    public void beforeMethod() {
        testAlbum = new Album();
        testAlbum.setTitle("Test Album");
        testAlbum.setReleaseDate(LocalDate.now());
        testAlbum.setArtistId(1L);
        System.out.println("Test album created");
    }

    @Test
    public void testCreateAlbum() {
        albumService.create(testAlbum);
        Assert.assertNotNull(testAlbum.getId(), "Album id should not be null after creation");
    }

    @Test
    public void testFindAlbumById() {
        albumService.create(testAlbum);
        Album found = albumService.findById(testAlbum.getId()).orElse(null);
        Assert.assertNotNull(found, "Album should be found by id");
    }

    @Test
    public void testFindAllAlbums() {
        List<Album> albums = albumService.findAll();
        Assert.assertFalse(albums.isEmpty(), "Albums list should not be empty");
    }

    @Test
    public void testFindAlbumsByArtistId() {
        albumService.create(testAlbum);
        List<Album> albums = albumService.findByArtistId(1L);
        Assert.assertFalse(albums.isEmpty(), "Should find albums by artist id");
    }

    @Test
    public void testUpdateAlbum() {
        albumService.create(testAlbum);
        testAlbum.setTitle("Updated Album");
        Album updated = albumService.update(testAlbum);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updated.getTitle(), "Updated Album", "Title should be updated");
        softAssert.assertNotNull(updated.getReleaseDate(), "Release date should not be null");
        softAssert.assertAll();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateAlbumWithEmptyTitle() {
        testAlbum.setTitle("");
        albumService.create(testAlbum);
    }

    @AfterMethod
    public void afterMethod() {
        if (testAlbum.getId() != null) {
            albumService.delete(testAlbum.getId());
        }
        System.out.println("Test album cleaned up");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AlbumService tests completed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Album Service Test Suite finished");
    }
}