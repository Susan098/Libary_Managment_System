package com.example.library.utils;

import com.example.library.model.User;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private static Map<String, User> users = new HashMap<>();
    private static int nextUserId = 1;
    
    static {
    
        User admin = new User(nextUserId++, "Admin", "admin", "admin123", "admin@library.com", true);
        users.put(admin.getUsername(), admin);
        
        
        User user1 = new User(nextUserId++, "John Doe", "john", "password123", "john@email.com", false);
        users.put(user1.getUsername(), user1);
        
        User user2 = new User(nextUserId++, "Jane Smith", "jane", "password123", "jane@email.com", false);
        users.put(user2.getUsername(), user2);
    }
    
    public static User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public static boolean register(User user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        user.setId(nextUserId++);
        users.put(user.getUsername(), user);
        return true;
    }
}