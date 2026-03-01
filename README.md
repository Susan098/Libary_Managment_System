# Library Management System

A simple console-based Library Management System built with Java.

## Features
- User Registration and Login
- Add new books
- View all books
- View available books
- Search books by subject or ID
- Borrow books
- Return books
- View borrowed books

## Book Subjects
- Computer Science (3 books)
- Chemistry (3 books)
- Physics (3 books)
- English (3 books)
- Nepali (3 books)
- Mathematics (3 books)

## Default Login
- Username: admin
- Password: admin123

## How to Run
1. Compile: `javac com/example/library/**/*.java com/example/library/*.java`
2. Run: `java com.example.library.Main`

## Project Structure
- model/ - Book and Member classes
- service/ - Business logic
- controller/ - Handles user requests
- utils/ - Input utilities