package com.example.library.service;

import com.example.library.dao.BookDao;
import com.example.library.model.Book;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();
    
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
    
    public List<Book> searchBySubject(String subject) {
        return bookDao.searchBySubject(subject);
    }
    
    public List<Book> getAvailableBooks() {
        return bookDao.getAvailableBooks();
    }
    
    public boolean addBook(String title, String author, String subject, String isbn) {
        Book book = new Book(0, title, author, subject, isbn, true);
        return bookDao.addBook(book);
    }
    
    public Book getBookById(int bookId) {
        return bookDao.getBookById(bookId);
    }
    
    public boolean updateBookAvailability(int bookId, boolean available) {
        return bookDao.updateBookAvailability(bookId, available);
    }
}