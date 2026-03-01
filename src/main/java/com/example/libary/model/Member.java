package com.example.library.model;

import java.util.ArrayList;

public class Member {
    private String username;
    private String password;
    private String name;
    private String email;
    private ArrayList<Integer> borrowedBooks;
    
    public Member(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }
    

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public ArrayList<Integer> getBorrowedBooks() { return borrowedBooks; }
    
    public void borrowBook(int bookId) {
        borrowedBooks.add(bookId);
    }
    
    public void returnBook(int bookId) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i) == bookId) {
                borrowedBooks.remove(i);
                break;
            }
        }
    }
    
    public int hasBorrowedBook(int bookId) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i) == bookId) {
                return 1;
            }
        }
        return 0;
    }
}