package com.epam.service.uritemplate;

public class UserServiceUri {
    public static final UriTemplate USER = new UriTemplate("user");
    public static final UriTemplate USER_BY_USERNAME = new UriTemplate("user/%s");
    public static final UriTemplate USER_LOGIN = new UriTemplate("user/login");
}