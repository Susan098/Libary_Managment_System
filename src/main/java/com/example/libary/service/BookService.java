package com.example.library.service;

import com.example.library.model.Book;
import java.util.ArrayList;

public class BookService {
    private ArrayList<Book> books;
    private int nextId;
    
    public BookService() {
        books = new ArrayList<>();
        nextId = 1;
        initializeBooks();
    }
    
    private void initializeBooks() {
        // Computer Science Books
        addBook("Introduction to Computer Science", "John Smith", "Computer Science");
        addBook("Operating Systems", "Jane Doe", "Computer Science");
        addBook("Java Programming", "James Gosling", "Computer Science");
        
        // Chemistry Books
        addBook("Chemistry Fundamentals", "Marie Curie", "Chemistry");
        addBook("Organic Chemistry", "Robert Brown", "Chemistry");
        addBook("Physical Chemistry", "Peter Atkins", "Chemistry");
        
        // Physics Books
        addBook("Physics for Beginners", "Albert Einstein", "Physics");
        addBook("Quantum Mechanics", "Niels Bohr", "Physics");
        addBook("Classical Physics", "Isaac Newton", "Physics");
        
        // English Books
        addBook("English Grammar", "William Shakespeare", "English");
        addBook("English Literature", "Charles Dickens", "English");
        addBook("Business English", "Jane Austen", "English");
        
        // Nepali Books
        addBook("Nepali Language", "Laxmi Prasad Devkota", "Nepali");
        addBook("Nepali Literature", "Parasmani Pradhan", "Nepali");
        addBook("Nepali Grammar", "Bhanubhakta Acharya", "Nepali");
        
        // Mathematics Books
        addBook("Mathematics", "Ramanujan", "Mathematics");
        addBook("Calculus", "Isaac Newton", "Mathematics");
        addBook("Algebra", "Euclid", "Mathematics");
    }
    
    public void addBook(String title, String author, String subject) {
        Book book = new Book(nextId, title, author, subject);
        books.add(book);
        System.out.println("Book added successfully! Book ID: " + nextId);
        nextId++;
    }
    
    public ArrayList<Book> getAllBooks() {
        return books;
    }
    
    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getAvailable() == 1) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    
    public ArrayList<Book> getBooksBySubject(String subject) {
        ArrayList<Book> subjectBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getSubject().equalsIgnoreCase(subject)) {
                subjectBooks.add(book);
            }
        }
        return subjectBooks;
    }
    
    public Book getBookById(int id) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    
    public int borrowBook(int bookId, String username) {
        Book book = getBookById(bookId);
        if (book != null && book.getAvailable() == 1) {
            book.setAvailable(0);
            book.setBorrowedBy(username);
            return 1; // Success
        }
        return 0; // Failed
    }
    
    public int returnBook(int bookId) {
        Book book = getBookById(bookId);
        if (book != null && book.getAvailable() == 0) {
            book.setAvailable(1);
            book.setBorrowedBy("");
            return 1; // Success
        }
        return 0; // Failed
    }
    
    public ArrayList<Book> getBooksBorrowedByUser(String username) {
        ArrayList<Book> userBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getAvailable() == 0 && username.equals(book.getBorrowedBy())) {
                userBooks.add(book);
            }
        }
        return userBooks;
    }
}