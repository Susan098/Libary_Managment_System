package com.example.library.dao;

import com.example.library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private List<Book> books = new ArrayList<>();

    public BookDao() {
        
        books.add(new Book(1, "Engineering Mathematics", "Bhim sir", "Math", 5));
        books.add(new Book(2, "General Science", "Mina miss", "Nepali", 4));
        books.add(new Book(3, "Java Programming", "Apar sir", "Java", 6));
        books.add(new Book(4, "Computer Organization", "hhh", "COA", 3));
        books.add(new Book(5, "Operating System Concepts", "iii", "OS", 5));
        books.add(new Book(6, "Organic Chemistry", "mjjj", "Chemistry", 2));
        books.add(new Book(7, "University Physics", "Young & Freedman", "Bhim sir", 4));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }

    public List<Book> searchBySubject(String subject) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getSubject().equalsIgnoreCase(subject)) {
                result.add(b);
            }
        }
        return result;
    }
}
