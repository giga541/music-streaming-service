package com.solvd.musicstreamingservice.test;

import com.solvd.musicstreamingservice.factory.MyBatisRepositoryFactory;
import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.model.User;
import com.solvd.musicstreamingservice.service.UserService;
import com.solvd.musicstreamingservice.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Listeners(com.solvd.musicstreamingservice.listener.TestNGListener.class)
public class UserServiceTest {

    private UserService userService;
    private User testUser;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Starting User Service Test Suite");
    }

    @BeforeClass
    public void beforeClass() {
        RepositoryFactory factory = new MyBatisRepositoryFactory();
        userService = new UserServiceImpl(factory);
        System.out.println("UserService initialized");
    }

    @BeforeMethod
    public void beforeMethod() {
        testUser = new User.Builder()
                .username("test_user")
                .email("test_" + System.currentTimeMillis() + "@email.com")
                .premium(true)
                .registrationDate(LocalDate.now())
                .lastLogin(LocalDateTime.now())
                .musicServiceId(1L)
                .build();
        System.out.println("Test user created");
    }

    @Test
    public void testCreateUser() {
        userService.create(testUser);
        Assert.assertNotNull(testUser.getId(), "User id should not be null after creation");
    }

    @Test
    public void testFindUserById() {
        userService.create(testUser);
        User found = userService.findById(testUser.getId()).orElse(null);
        Assert.assertNotNull(found, "User should be found by id");
    }

    @Test
    public void testUpdateUser() {
        userService.create(testUser);
        testUser.setUsername("updated_user");
        User updated = userService.update(testUser);
        Assert.assertEquals(updated.getUsername(), "updated_user", "Username should be updated");
    }

    @Test
    public void testFindAllPremiumUsers() {
        userService.create(testUser);
        List<User> premiumUsers = userService.findAllPremium();
        Assert.assertFalse(premiumUsers.isEmpty(), "Premium users list should not be empty");
    }

    @Test
    public void testCreateUserSoftAssert() {
        userService.create(testUser);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(testUser.getId(), "Id should not be null");
        softAssert.assertEquals(testUser.getUsername(), "test_user", "Username should match");
        softAssert.assertTrue(testUser.premium(), "User should be premium");
        softAssert.assertAll();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateUserWithEmptyEmail() {
        testUser.setEmail("");
        userService.create(testUser);
    }

    @AfterMethod
    public void afterMethod() {
        if (testUser.getId() != null) {
            userService.delete(testUser.getId());
        }
        System.out.println("Test user cleaned up");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("UserService tests completed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("User Service Test Suite finished");
    }
}