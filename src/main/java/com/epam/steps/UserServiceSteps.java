package com.epam.steps;

import com.epam.entities.User;
import com.epam.service.UserService;
import com.epam.service.uritemplate.UriTemplate;
import io.restassured.response.Response;

import static com.epam.service.uritemplate.UserServiceUri.*;

public class UserServiceSteps {
    private static final UserService USER_SERVICE = UserService.getInstance();

    public static Response getUserByUsername(String username) {
        return USER_SERVICE.getRequest(USER_BY_USERNAME, username);
    }

    public static Response login(String username, String password) {
        return USER_SERVICE.getRequest(USER_LOGIN, username, password);
    }

    public static Response createUser(User user) {
        return USER_SERVICE.postRequest(USER, user);
    }

    public static void deleteUserByUsername(String username) {
        USER_SERVICE.deleteRequest(USER_BY_USERNAME, username);
    }
}