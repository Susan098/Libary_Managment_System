package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.LibraryService;
import com.example.library.utils.InputUtil;

import java.util.List;

public class LibraryController {

    private LibraryService service;

    public LibraryController() {
        service = new LibraryService();
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. View All Books");
            System.out.println("2. Add Book");
            System.out.println("3. Search by Subject");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            choice = InputUtil.getInt("Enter choice: ");

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    searchBooks();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    System.out.println("Exiting... Thank You!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    private void viewBooks() {
        List<Book> books = service.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book b : books) b.display();
        }
    }

    private void addBook() {
        int id = InputUtil.getInt("Enter Book ID: ");
        String title = InputUtil.getString("Enter Title: ");
        String author = InputUtil.getString("Enter Author: ");
        String subject = InputUtil.getString("Enter Subject: ");
        int qty = InputUtil.getInt("Enter Quantity: ");

        Book book = new Book(id, title, author, subject, qty);
        service.addBook(book);
    }

    private void searchBooks() {
        String subject = InputUtil.getString("Enter Subject to search: ");
        List<Book> books = service.searchBySubject(subject);
        if (books.isEmpty()) {
            System.out.println("No books found for subject: " + subject);
        } else {
            for (Book b : books) b.display();
        }
    }

    private void deleteBook() {
        int id = InputUtil.getInt("Enter Book ID to delete: ");
        service.deleteBook(id);
    }
}
