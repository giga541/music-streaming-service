package com.solvd.musicstreamingservice.test;

import com.solvd.musicstreamingservice.factory.MyBatisRepositoryFactory;
import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.model.Artist;
import com.solvd.musicstreamingservice.service.ArtistService;
import com.solvd.musicstreamingservice.service.impl.ArtistServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Listeners(com.solvd.musicstreamingservice.listener.TestNGListener.class)
public class ArtistServiceTest {

    private ArtistService artistService;
    private Artist testArtist;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Starting Artist Service Test Suite");
    }

    @BeforeClass
    public void beforeClass() {
        RepositoryFactory factory = new MyBatisRepositoryFactory();
        artistService = new ArtistServiceImpl(factory);
        System.out.println("ArtistService initialized");
    }

    @BeforeMethod
    public void beforeMethod() {
        testArtist = new Artist();
        testArtist.setName("Test Artist");
        testArtist.setCountry("UK");
        testArtist.setMusicServiceId(1L);
        System.out.println("Test artist created");
    }

    @Test
    public void testCreateArtist() {
        artistService.create(testArtist);
        Assert.assertNotNull(testArtist.getId(), "Artist id should not be null after creation");
    }

    @Test
    public void testFindArtistById() {
        artistService.create(testArtist);
        Artist found = artistService.findById(testArtist.getId()).orElse(null);
        Assert.assertNotNull(found, "Artist should be found by id");
    }

    @Test
    public void testFindAllArtists() {
        List<Artist> artists = artistService.findAll();
        Assert.assertFalse(artists.isEmpty(), "Artists list should not be empty");
    }

    @Test
    public void testFindArtistsByCountry() {
        artistService.create(testArtist);
        List<Artist> artists = artistService.findByCountry("UK");
        Assert.assertFalse(artists.isEmpty(), "Should find artists from UK");
    }

    @Test
    public void testUpdateArtist() {
        artistService.create(testArtist);
        testArtist.setName("Updated Artist");
        Artist updated = artistService.update(testArtist);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updated.getName(), "Updated Artist", "Name should be updated");
        softAssert.assertEquals(updated.getCountry(), "UK", "Country should remain the same");
        softAssert.assertAll();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateArtistWithEmptyName() {
        testArtist.setName("");
        artistService.create(testArtist);
    }

    @AfterMethod
    public void afterMethod() {
        if (testArtist.getId() != null) {
            artistService.delete(testArtist.getId());
        }
        System.out.println("Test artist cleaned up");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("ArtistService tests completed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Artist Service Test Suite finished");
    }
}