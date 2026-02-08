package com.example.library.dao;

import com.example.library.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) { users.add(user); }
    public List<User> getAllUsers() { return users; }
    public User getUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }
}
