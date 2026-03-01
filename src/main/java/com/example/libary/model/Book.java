package com.example.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String subject;
    private int quantity;

    public Book(int id, String title, String author, String subject, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getSubject() { return subject; }
    public int getQuantity() { return quantity; }

    public void display() {
        System.out.println("--------------------------------");
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Subject: " + subject);
        System.out.println("Quantity: " + quantity);
    }
}
