package com.example.library.service;

import com.example.library.dao.BookDao;
import com.example.library.model.Book;
import java.util.List;

public class MemberService {
    private BookDao bookDao = new BookDao();
    
    public List<Book> getBorrowedBooks(int userId) {
        return bookDao.getBorrowedBooks(userId);
    }
    
    public List<BookDao.BorrowRecord> getBorrowHistory(int userId) {
        return bookDao.getBorrowHistory(userId);
    }
    
    public boolean hasBorrowedBook(int userId, int bookId) {
        return bookDao.getBorrowedBooks(userId).stream()
            .anyMatch(book -> book.getId() == bookId);
    }
}