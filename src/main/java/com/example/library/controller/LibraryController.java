package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.LibraryService;
import com.example.library.utils.InputUtil;

public class LibraryController {
    private LibraryService service = new LibraryService();

    public void start() {
        int choice;
        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Display Books");
            System.out.println("4. Add User");
            System.out.println("5. Display Users");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            choice = InputUtil.readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> deleteBook();
                case 3 -> displayBooks();
                case 4 -> addUser();
                case 5 -> displayUsers();
                case 6 -> issueBook();
                case 7 -> returnBook();
                case 8 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 8);
    }

    private void addBook() {
        int id = InputUtil.readInt("Book ID: ");
        String title = InputUtil.readString("Title: ");
        String author = InputUtil.readString("Author: ");
        int qty = InputUtil.readInt("Quantity: ");
        service.addBook(new Book(id, title, author, qty));
        System.out.println("Book added!");
    }

    private void deleteBook() {
        int id = InputUtil.readInt("Book ID to delete: ");
        service.deleteBook(id);
        System.out.println("Book deleted!");
    }

    private void displayBooks() {
        service.getAllBooks().forEach(System.out::println);
    }

    private void addUser() {
        int id = InputUtil.readInt("User ID: ");
        String name = InputUtil.readString("Name: ");
        String contact = InputUtil.readString("Contact: ");
        service.addUser(new User(id, name, contact));
        System.out.println("User added!");
    }

    private void displayUsers() {
        service.getAllUsers().forEach(System.out::println);
    }

    private void issueBook() {
        int bookId = InputUtil.readInt("Book ID: ");
        if (service.issueBook(bookId)) System.out.println("Book issued!");
        else System.out.println("Cannot issue book. Check ID or quantity.");
    }

    private void returnBook() {
        int bookId = InputUtil.readInt("Book ID: ");
        service.returnBook(bookId);
        System.out.println("Book returned!");
    }
}
