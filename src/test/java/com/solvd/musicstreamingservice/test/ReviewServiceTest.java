package com.solvd.musicstreamingservice.test;

import com.solvd.musicstreamingservice.factory.MyBatisRepositoryFactory;
import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.model.Review;
import com.solvd.musicstreamingservice.service.ReviewService;
import com.solvd.musicstreamingservice.service.impl.ReviewServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.LocalDateTime;
import java.util.List;

@Listeners(com.solvd.musicstreamingservice.listener.TestNGListener.class)
public class ReviewServiceTest {

    private ReviewService reviewService;
    private Review testReview;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Starting Review Service Test Suite");
    }

    @BeforeClass
    public void beforeClass() {
        RepositoryFactory factory = new MyBatisRepositoryFactory();
        reviewService = new ReviewServiceImpl(factory);
        System.out.println("ReviewService initialized");
    }

    @BeforeMethod
    public void beforeMethod() {
        testReview = new Review();
        testReview.setRating(5);
        testReview.setComment("Great song!");
        testReview.setCreatedAt(LocalDateTime.now());
        testReview.setSongId(1L);
        System.out.println("Test review created");
    }

    @Test
    public void testCreateReview() {
        reviewService.create(testReview);
        Assert.assertNotNull(testReview.getId(), "Review id should not be null after creation");
    }

    @Test
    public void testFindReviewById() {
        reviewService.create(testReview);
        Review found = reviewService.findById(testReview.getId()).orElse(null);
        Assert.assertNotNull(found, "Review should be found by id");
    }

    @Test
    public void testFindReviewsBySongId() {
        reviewService.create(testReview);
        List<Review> reviews = reviewService.findBySongId(1L);
        Assert.assertFalse(reviews.isEmpty(), "Should find reviews by song id");
    }

    @Test
    public void testFindReviewsByRating() {
        reviewService.create(testReview);
        List<Review> reviews = reviewService.findByRatingGreaterThan(3);
        Assert.assertFalse(reviews.isEmpty(), "Should find reviews with rating greater than 3");
    }

    @Test
    public void testUpdateReview() {
        reviewService.create(testReview);
        testReview.setRating(4);
        testReview.setComment("Updated comment");
        Review updated = reviewService.update(testReview);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updated.getRating(), 4, "Rating should be updated");
        softAssert.assertEquals(updated.getComment(), "Updated comment", "Comment should be updated");
        softAssert.assertAll();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateReviewWithInvalidRating() {
        testReview.setRating(6);
        reviewService.create(testReview);
    }

    @AfterMethod
    public void afterMethod() {
        if (testReview.getId() != null) {
            reviewService.delete(testReview.getId());
        }
        System.out.println("Test review cleaned up");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("ReviewService tests completed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Review Service Test Suite finished");
    }
}