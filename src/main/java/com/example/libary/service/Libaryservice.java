package com.example.library.service;

import com.example.library.dao.BookDao;
import com.example.library.model.Book;

import java.util.List;

public class LibraryService {
    private BookDao bookDao;

    public LibraryService() {
        this.bookDao = new BookDao();
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
        System.out.println("Book added successfully!");
    }

    public void deleteBook(int id) {
        bookDao.deleteBook(id);
        System.out.println("Book deleted successfully (if ID existed)!");
    }

    public List<Book> searchBySubject(String subject) {
        return bookDao.searchBySubject(subject);
    }
}
