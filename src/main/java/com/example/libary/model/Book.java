package com.example.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String subject;
    private int available; // 1 for available, 0 for borrowed
    private String borrowedBy;
    
    public Book(int id, String title, String author, String subject) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.available = 1;
        this.borrowedBy = "";
    }
    

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    
    public int getAvailable() { return available; }
    public void setAvailable(int available) { this.available = available; }
    
    public String getBorrowedBy() { return borrowedBy; }
    public void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }
    
    public String getStatus() {
        if (available == 1) {
            return "Available";
        } else {
            return "Borrowed by " + borrowedBy;
        }
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Subject: %s | Status: %s", 
            id, title, author, subject, getStatus());
    }
}