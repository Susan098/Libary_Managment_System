package com.example.library.controller;

import com.example.library.service.BookService;
import com.example.library.service.MemberService;
import com.example.library.model.Book;
import com.example.library.utils.InputUtils;
import java.util.ArrayList;

public class LibraryController {
    private BookService bookService;
    private MemberService memberService;
    
    public LibraryController() {
        this.bookService = new BookService();
        this.memberService = new MemberService();
    }
    
    public int login(String username, String password) {
        return memberService.login(username, password);
    }
    
    public int register(String username, String password, String name, String email) {
        return memberService.register(username, password, name, email);
    }
    
    public void logout() {
        memberService.logout();
    }
    
    public int isLoggedIn() {
        return memberService.isLoggedIn();
    }
    
    public void displayAllBooks() {
        ArrayList<Book> books = bookService.getAllBooks();
        if (books.size() == 0) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n=== ALL BOOKS ===");
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }
    
    public void displayAvailableBooks() {
        ArrayList<Book> books = bookService.getAvailableBooks();
        if (books.size() == 0) {
            System.out.println("No books available at the moment.");
        } else {
            System.out.println("\n=== AVAILABLE BOOKS ===");
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }
    
    public void displayBooksBySubject(String subject) {
        ArrayList<Book> books = bookService.getBooksBySubject(subject);
        if (books.size() == 0) {
            System.out.println("No books found in " + subject + " subject.");
        } else {
            System.out.println("\n=== " + subject.toUpperCase() + " BOOKS ===");
            for (int i = 0; i < books.size(); i++) {
                System.out.println(books.get(i));
            }
        }
    }
    
    public void displaySubjects() {
        System.out.println("\n=== AVAILABLE SUBJECTS ===");
        System.out.println("1. Computer Science");
        System.out.println("2. Chemistry");
        System.out.println("3. Physics");
        System.out.println("4. English");
        System.out.println("5. Nepali");
        System.out.println("6. Mathematics");
    }
    
    public void addBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        String title = InputUtils.getString("Enter book title: ");
        String author = InputUtils.getString("Enter author name: ");
        
        displaySubjects();
        int subjectChoice = InputUtils.getInt("Select subject (1-6): ");
        
        String subject = getSubjectFromChoice(subjectChoice);
        if (subject != null) {
            bookService.addBook(title, author, subject);
        } else {
            System.out.println("Invalid subject choice!");
        }
    }
    
    public void borrowBook() {
        displayAvailableBooks();
        int bookId = InputUtils.getInt("\nEnter book ID to borrow: ");
        
        int result = bookService.borrowBook(bookId, memberService.getCurrentUser().getUsername());
        if (result == 1) {
            memberService.addBorrowedBook(bookId);
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Failed to borrow book. Book might not be available or invalid ID.");
        }
    }
    
    public void returnBook() {
        ArrayList<Book> borrowedBooks = bookService.getBooksBorrowedByUser(
            memberService.getCurrentUser().getUsername());
        
        if (borrowedBooks.size() == 0) {
            System.out.println("You haven't borrowed any books.");
            return;
        }
        
        System.out.println("\n=== YOUR BORROWED BOOKS ===");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println(borrowedBooks.get(i));
        }
        
        int bookId = InputUtils.getInt("\nEnter book ID to return: ");
        
        int result = bookService.returnBook(bookId);
        if (result == 1) {
            memberService.removeBorrowedBook(bookId);
            System.out.println("Book returned successfully! Thank you!");
        } else {
            System.out.println("Failed to return book. Please check the book ID.");
        }
    }
    
    public void displayMyBooks() {
        ArrayList<Book> borrowedBooks = bookService.getBooksBorrowedByUser(
            memberService.getCurrentUser().getUsername());
        
        if (borrowedBooks.size() == 0) {
            System.out.println("You haven't borrowed any books.");
        } else {
            System.out.println("\n=== BOOKS YOU HAVE BORROWED ===");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println(borrowedBooks.get(i));
            }
        }
    }
    
    public void searchBooks() {
        System.out.println("\n=== SEARCH BOOKS ===");
        System.out.println("1. Search by Subject");
        System.out.println("2. Search by ID");
        System.out.println("3. View All Books");
        
        int choice = InputUtils.getInt("Enter your choice: ");
        
        if (choice == 1) {
            displaySubjects();
            int subjectChoice = InputUtils.getInt("Select subject (1-6): ");
            String subject = getSubjectFromChoice(subjectChoice);
            if (subject != null) {
                displayBooksBySubject(subject);
            } else {
                System.out.println("Invalid subject choice!");
            }
        } else if (choice == 2) {
            int bookId = InputUtils.getInt("Enter book ID: ");
            Book book = bookService.getBookById(bookId);
            if (book != null) {
                System.out.println(book);
            } else {
                System.out.println("Book not found!");
            }
        } else if (choice == 3) {
            displayAllBooks();
        } else {
            System.out.println("Invalid choice!");
        }
    }
    
    private String getSubjectFromChoice(int choice) {
        if (choice == 1) return "Computer Science";
        else if (choice == 2) return "Chemistry";
        else if (choice == 3) return "Physics";
        else if (choice == 4) return "English";
        else if (choice == 5) return "Nepali";
        else if (choice == 6) return "Mathematics";
        else return null;
    }
}