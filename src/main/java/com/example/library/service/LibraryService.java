package com.example.library.service;

import com.example.library.dao.BookDAO;
import com.example.library.dao.UserDAO;
import com.example.library.model.Book;
import com.example.library.model.User;
import java.util.List;

public class LibraryService {
    private BookDAO bookDAO = new BookDAO();
    private UserDAO userDAO = new UserDAO();

    // BOOK METHODS
    public void addBook(Book book) { bookDAO.addBook(book); }
    public void deleteBook(int id) { bookDAO.deleteBook(id); }
    public List<Book> getAllBooks() { return bookDAO.getAllBooks(); }
    public Book getBookById(int id) { return bookDAO.getBookById(id); }

    // USER METHODS
    public void addUser(User user) { userDAO.addUser(user); }
    public List<User> getAllUsers() { return userDAO.getAllUsers(); }
    public User getUserById(int id) { return userDAO.getUserById(id); }

    // ISSUE & RETURN BOOKS
    public boolean issueBook(int bookId) {
        Book book = getBookById(bookId);
        if (book != null && book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            return true;
        }
        return false;
    }

    public void returnBook(int bookId) {
        Book book = getBookById(bookId);
        if (book != null) book.setQuantity(book.getQuantity() + 1);
    }
}
