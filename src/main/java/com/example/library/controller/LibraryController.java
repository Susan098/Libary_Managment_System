package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.MemberService;
import com.example.library.service.LibraryService;
import com.example.library.utils.InputUtils;
import com.example.library.utils.Login;
import com.example.library.utils.Register;

public class LibraryController {
    private User currentUser = null;
    private BookService bookService = new BookService();
    private MemberService memberService = new MemberService();
    private LibraryService libraryService = new LibraryService();
    
    public void start() {
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }
    
    private void showLoginMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        
        int choice = InputUtils.getIntInput();
        
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("Thank you ");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option! Please try again.");
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n=== WELCOME, " + currentUser.getName() + " ===");
        System.out.println("1. View All Books");
        System.out.println("2. Search Books");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. View Available Books");
        System.out.println("6. View My Borrowed Books");
        System.out.println("7. Borrow History");
        System.out.println("8. Add Book");
        System.out.println("9. Exit");
        System.out.print("Choose option: ");
        
        int choice = InputUtils.getIntInput();
        
        switch (choice) {
            case 1:
                viewAllBooks();
                break;
            case 2:
                searchBooks();
                break;
            case 3:
                borrowBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                viewAvailableBooks();
                break;
            case 6:
                viewMyBorrowedBooks();
                break;
            case 7:
                viewBorrowHistory();
                break;
            case 8:
                addBook();
                break;
            case 9:
                logout();
                break;
            default:
                System.out.println("Invalid option! Please try again.");
        }
    }
    
    private void login() {
        System.out.print("Enter username: ");
        String username = InputUtils.getStringInput();
        System.out.print("Enter password: ");
        String password = InputUtils.getStringInput();
        
        User user = Login.authenticate(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful! Welcome " + user.getName());
        } else {
            System.out.println("Invalid username or password!");
        }
    }
    
    private void register() {
        System.out.print("Enter name: ");
        String name = InputUtils.getStringInput();
        System.out.print("Enter username: ");
        String username = InputUtils.getStringInput();
        System.out.print("Enter password: ");
        String password = InputUtils.getStringInput();
        System.out.print("Enter email: ");
        String email = InputUtils.getStringInput();
        
        if (Register.registerUser(name, username, password, email)) {
            System.out.println("Registration successful! Please login.");
        } else {
            System.out.println("Registration failed! Username may already exist.");
        }
    }
    
    private void viewAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        bookService.getAllBooks().forEach(System.out::println);
    }
    
    private void searchBooks() {
        System.out.println("\nSubjects available:");
        System.out.println("java, Math, Chemistry, Physics, COA, OS, WMAD, English, Nepali");
        System.out.print("Enter subject to search: ");
        String subject = InputUtils.getStringInput();
        
        bookService.searchBySubject(subject).forEach(System.out::println);
    }
    
    private void borrowBook() {
        viewAvailableBooks();
        System.out.print("Enter Book ID to borrow: ");
        int bookId = InputUtils.getIntInput();
        
        if (libraryService.borrowBook(bookId, currentUser.getId())) {
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Failed to borrow book. Book may not be available.");
        }
    }
    
    private void returnBook() {
        viewMyBorrowedBooks();
        System.out.print("Enter Book ID to return: ");
        int bookId = InputUtils.getIntInput();
        
        if (libraryService.returnBook(bookId, currentUser.getId())) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Failed to return book. Please check Book ID.");
        }
    }
    
    private void viewAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS ===");
        bookService.getAvailableBooks().forEach(System.out::println);
    }
    
    private void viewMyBorrowedBooks() {
        System.out.println("\n=== MY BORROWED BOOKS ===");
        memberService.getBorrowedBooks(currentUser.getId()).forEach(System.out::println);
    }
    
    private void viewBorrowHistory() {
        System.out.println("\n=== BORROW HISTORY ===");
        memberService.getBorrowHistory(currentUser.getId()).forEach(System.out::println);
    }
    
    private void addBook() {
        if (!currentUser.isAdmin()) {
            System.out.println("Admin access required!");
            return;
        }
        
        System.out.println("\n=== ADD NEW BOOK ===");
        System.out.print("Enter book title: ");
        String title = InputUtils.getStringInput();
        System.out.print("Enter author: ");
        String author = InputUtils.getStringInput();
        System.out.print("Enter subject: ");
        String subject = InputUtils.getStringInput();
        System.out.print("Enter ISBN: ");
        String isbn = InputUtils.getStringInput();
        
        if (bookService.addBook(title, author, subject, isbn)) {
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Failed to add book.");
        }
    }
    
    private void logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
    }
}