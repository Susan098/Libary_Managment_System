package com.example.library.utils;

import com.example.library.model.User;

public class Register {
    public static boolean registerUser(String name, String username, String password, String email) {
        User newUser = new User(0, name, username, password, email, false);
        return Login.register(newUser);
    }
}