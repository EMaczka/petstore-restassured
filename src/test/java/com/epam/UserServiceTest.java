package com.epam;

import com.epam.entities.User;
import com.epam.log.Log;
import com.epam.steps.UserServiceSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserServiceTest {
    User user = createUser();

    @Test
    public void loginTest() {
        Log.info("User login test starts...");
        Response responseLogin = UserServiceSteps.login(user.getUsername(), user.getPassword());
        Assert.assertEquals(responseLogin.getStatusCode(), 200,
                "Status code is not as expected");
        Log.info(user + " logged in successfully!");
    }

    @Test
    public void createUsersTest() {
        Log.info("User creation test starts...");
        Response responseCreatedUser = UserServiceSteps.createUser(user);
        Assert.assertEquals(responseCreatedUser.getStatusCode(), 200,
                "Status code is not as expected\"");
        Log.info(user + " created successfully!");
    }

    @Test
    public void getUserByUsernameTest() {
        Log.info("Get user test starts...");
        Response responseCreatedUser = UserServiceSteps.createUser(user);
        User createdUser = UserServiceSteps.getUserByUsername(user.getUsername()).as(User.class);
        Assert.assertEquals(createdUser.getId(), user.getId(),
                "User ID is incorrect");
        Assert.assertEquals(createdUser.getUsername(), user.getUsername(),
                "Username is incorrect");
        Log.info(user + " retrieved successfully!");
    }

    @Test
    public void deleteUserTest() {
        Log.info("Delete user test starts...");
        Response responseCreatedUser = UserServiceSteps.createUser(user);
        UserServiceSteps.deleteUserByUsername(user.getUsername());
        Response responseDeletedUser = UserServiceSteps.getUserByUsername(user.getUsername());
        Assert.assertEquals(responseDeletedUser.getStatusCode(), 404,
                "User is not deleted");
        Log.info(user + " deleted successfully!");
    }

    private User createUser() {
        return new User()
                .setId(1)
                .setUsername("user123")
                .setFirstName("Sue")
                .setLastName("Smith")
                .setEmail("example@test.com")
                .setPassword("zaq1@WSX")
                .setPhone("126060606")
                .setUserStatus(1);
    }
}
