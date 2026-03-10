package com.example.library.service;

import com.example.library.dao.BookDao;

public class LibraryService {
    private BookDao bookDao = new BookDao();
    private BookService bookService = new BookService();
    private MemberService memberService = new MemberService();
    
    public boolean borrowBook(int bookId, int userId) {
    
        if (bookService.getBookById(bookId) != null && 
            bookService.getBookById(bookId).isAvailable()) {
            return bookDao.borrowBook(bookId, userId);
        }
        return false;
    }
    
    public boolean returnBook(int bookId, int userId) {
        // Check if user has borrowed this book
        if (memberService.hasBorrowedBook(userId, bookId)) {
            return bookDao.returnBook(bookId, userId);
        }
        return false;
    }
    
    public boolean isBookAvailable(int bookId) {
        return bookService.getBookById(bookId) != null && 
               bookService.getBookById(bookId).isAvailable();
    }
}