package com.example.library.service;

import com.example.library.model.Member;
import java.util.HashMap;

public class MemberService {
    private HashMap<String, Member> members;
    private Member currentUser;
    private int loggedIn; // 1 for logged in, 0 for not logged in
    
    public MemberService() {
        members = new HashMap<>();
        loggedIn = 0;
        // Add default admin user
        members.put("admin", new Member("admin", "admin123", "Administrator", "admin@library.com"));
    }
    
    public int register(String username, String password, String name, String email) {
        if (members.containsKey(username)) {
            return 0; // Username already exists
        }
        
        Member member = new Member(username, password, name, email);
        members.put(username, member);
        return 1; // Success
    }
    
    public int login(String username, String password) {
        Member member = members.get(username);
        if (member != null && member.getPassword().equals(password)) {
            currentUser = member;
            loggedIn = 1;
            return 1; // Success
        }
        return 0;
    }
    
    public void logout() {
        currentUser = null;
        loggedIn = 0;
    }
    
    public Member getCurrentUser() {
        return currentUser;
    }
    
    public int isLoggedIn() {
        return loggedIn;
    }
    
    public void addBorrowedBook(int bookId) {
        if (currentUser != null) {
            currentUser.borrowBook(bookId);
        }
    }
    
    public void removeBorrowedBook(int bookId) {
        if (currentUser != null) {
            currentUser.returnBook(bookId);
        }
    }
}