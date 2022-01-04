package com.example.natour21.API;

public class Config {

    //public static final String BASE_URL = "http://ec2-3-12-83-96.us-east-2.compute.amazonaws.com:8080";
    public static final String BASE_URL = "http://192.168.1.9:8080";
    public static final String API = "/api";
    public static final String LOGIN = "/login";
    public static final String REGISTER_NATOUR21 = "/user/register/natour21";
    public static final String REGISTER_FACEBOOK = "/user/register/facebook";
    public static final String REGISTER_GOOGLE = "/user/register/google";
    public static final String CHECK_AUTH = "/user/auth";
    public static final String LOGIN_FACEBOOK = "/user/facebook/login";
    public static final String LOGIN_GOOGLE = "/user/google/login";
    public static final String REFRESH_TOKEN = "/token/refresh";
    public static final String GET_CHAT_ROOMS = "/chatrooms";
    public static final String GET_MESSAGE_SINGLE_CHAT = "/message/chatroom";
    public static final String SEND_MESSAGE = "/message/send";
    public static final String GET_REPORTS = "/reports";
    public static final String SEND_REPORT_RESPONSE = "/report/update";
}
