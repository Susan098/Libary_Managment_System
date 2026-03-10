package com.example.library.dao;

import com.example.library.model.Book;
import java.util.*;
import java.util.stream.Collectors;

public class BookDao {
    private static List<Book> books = new ArrayList<>();
    private static Map<Integer, List<BorrowRecord>> borrowHistory = new HashMap<>();
    private static int nextId = 1;
    
    public BookDao() {
        if (books.isEmpty()) {
            initializeBooks();
        }
    }
    
    private void initializeBooks() {
        // Java books
        books.add(new Book(nextId++, "Java: The Good Parts", "Douglas Crockford", "java", "978-0596517748", true));
        books.add(new Book(nextId++, "java 2.o", "Marijn Haverbeke", "java", "978-1593279509", true));
        
        // Math books
        books.add(new Book(nextId++, "Calculus", "Bhimmmmm", "Math", "978-0538497817", true));
        books.add(new Book(nextId++, "Linear Algebra", "Bhimmmm", "Math", "978-1733146678", true));
        
        // Chemistry books
        books.add(new Book(nextId++, "Chemistry: The Central Science", "Sujan", "Chemistry", "978-0134414232", true));
        books.add(new Book(nextId++, "Organic Chemistry", "David Klein", "Shristi", "978-1119110477", true));
        
        // Physics books
        books.add(new Book(nextId++, "University Physics", "Dhoom Bhim", "Physics", "978-0321973610", true));
        books.add(new Book(nextId++, "Fundamentals of Physics", "Bhim", "Physics", "978-1118230725", true));
        
        // COA books
        books.add(new Book(nextId++, "Computer Organization", "RAjan", "COA", "978-0134997193", true));
        books.add(new Book(nextId++, "Computer Architecture", "NAbin", "COA", "978-0123838728", true));
        
        // OS books
        books.add(new Book(nextId++, "Operating Systems", "Sanam", "OS", "978-1119800361", true));
        books.add(new Book(nextId++, "Modern Operating Systems", "Devilal", "OS", "978-0133591620", true));
        
        // WMAD books
        books.add(new Book(nextId++, "Web Development", "Jon Duckett", "WMAD", "978-1118008188", true));
        books.add(new Book(nextId++, "Mobile App Development", "Saugat", "WMAD", "978-1234567890", true));
        
        // English books
        books.add(new Book(nextId++, "English Grammar", "Raymond Murphy", "English", "978-0521532891", true));
        books.add(new Book(nextId++, "er English", "Saubhagya ", "English", "978-0521532892", true));
        
        // Nepali books
        books.add(new Book(nextId++, "Nepali ", "Various Authors", "Nepali", "978-9993304537", true));
        books.add(new Book(nextId++, "Nepali + English", "MinaSapkota", "Nepali", "978-9993304538", true));
    }
    
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
    
    public List<Book> searchBySubject(String subject) {
        return books.stream()
            .filter(book -> book.getSubject().equalsIgnoreCase(subject))
            .collect(Collectors.toList());
    }
    
    public List<Book> getAvailableBooks() {
        return books.stream()
            .filter(Book::isAvailable)
            .collect(Collectors.toList());
    }
    
    public List<Book> getBorrowedBooks(int userId) {
        return borrowHistory.getOrDefault(userId, new ArrayList<>()).stream()
            .filter(record -> record.getReturnDate() == null)
            .map(BorrowRecord::getBook)
            .collect(Collectors.toList());
    }
    
    public boolean borrowBook(int bookId, int userId) {
        Optional<Book> bookOpt = books.stream()
            .filter(b -> b.getId() == bookId && b.isAvailable())
            .findFirst();
        
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setAvailable(false);
            
            BorrowRecord record = new BorrowRecord(book, userId, new Date(), null);
            borrowHistory.computeIfAbsent(userId, k -> new ArrayList<>()).add(record);
            return true;
        }
        return false;
    }
    
    public boolean returnBook(int bookId, int userId) {
        List<BorrowRecord> userRecords = borrowHistory.get(userId);
        if (userRecords != null) {
            Optional<BorrowRecord> recordOpt = userRecords.stream()
                .filter(r -> r.getBook().getId() == bookId && r.getReturnDate() == null)
                .findFirst();
            
            if (recordOpt.isPresent()) {
                BorrowRecord record = recordOpt.get();
                record.setReturnDate(new Date());
                record.getBook().setAvailable(true);
                return true;
            }
        }
        return false;
    }
    
    public List<BorrowRecord> getBorrowHistory(int userId) {
        return borrowHistory.getOrDefault(userId, new ArrayList<>());
    }
    
    public boolean addBook(Book book) {
        book.setId(nextId++);
        return books.add(book);
    }
    
    public Book getBookById(int bookId) {
        return books.stream()
            .filter(book -> book.getId() == bookId)
            .findFirst()
            .orElse(null);
    }
    
    public boolean updateBookAvailability(int bookId, boolean available) {
        Book book = getBookById(bookId);
        if (book != null) {
            book.setAvailable(available);
            return true;
        }
        return false;
    }
    

    public static class BorrowRecord {
        private Book book;
        private int userId;
        private Date borrowDate;
        private Date returnDate;
        
        public BorrowRecord(Book book, int userId, Date borrowDate, Date returnDate) {
            this.book = book;
            this.userId = userId;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
        }
        
        public Book getBook() { return book; }
        public int getUserId() { return userId; }
        public Date getBorrowDate() { return borrowDate; }
        public Date getReturnDate() { return returnDate; }
        public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
        
        @Override
        public String toString() {
            return String.format("Book: %s | Borrowed: %s | Returned: %s",
                book.getTitle(),
                borrowDate.toString(),
                returnDate == null ? "Not returned" : returnDate.toString());
        }
    }
}