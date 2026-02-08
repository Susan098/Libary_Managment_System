package com.example.library.dao;

import com.example.library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void deleteBook(int id) { books.removeIf(b -> b.getId() == id); }
    public List<Book> getAllBooks() { return books; }
    public Book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }
}
