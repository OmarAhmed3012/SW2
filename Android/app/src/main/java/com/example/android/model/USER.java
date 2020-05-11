package com.example.android.model;

public class USER {
    private static String Name = "" ;
    private static String token ;

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        USER.Name = name;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        USER.token ="Bearer "+ token;
    }
}

