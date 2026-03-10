package com.example.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String subject;
    private String isbn;
    private boolean available;
    
    public Book() {}
    
    public Book(int id, String title, String author, String subject, String isbn, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.isbn = isbn;
        this.available = available;
    }
    
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Subject: %s | ISBN: %s | %s",
            id, title, author, subject, isbn, available ? "Available" : "Borrowed");
    }
}