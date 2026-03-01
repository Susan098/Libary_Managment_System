

public class LibraryMenu {
    private LibraryController controller;
    
    public LibraryMenu() {
        this.controller = new LibraryController();
    }
    
    public void start() {
        while (true) {
            if (controller.isLoggedIn() == 0) {
                showMainMenu();
            } else {
                showUserMenu();
            }
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n=== WELCOME TO LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        
        int choice = InputUtils.getInt("Enter your choice: ");
        
        if (choice == 1) {
            login();
        } else if (choice == 2) {
            register();
        } else if (choice == 3) {
            System.out.println("Thank you for using Library Management System!");
            System.exit(0);
        } else {
            System.out.println("Invalid choice! Please try again.");
        }
    }
    
    private void showUserMenu() {
        System.out.println("\n=== LIBRARY MENU ===");
        System.out.println("1. View All Books");
        System.out.println("2. View Available Books");
        System.out.println("3. Search Books");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. View My Borrowed Books");
        System.out.println("7. Add New Book");
        System.out.println("8. Logout");
        
        int choice = InputUtils.getInt("Enter your choice: ");
        
        if (choice == 1) {
            controller.displayAllBooks();
        } else if (choice == 2) {
            controller.displayAvailableBooks();
        } else if (choice == 3) {
            controller.searchBooks();
        } else if (choice == 4) {
            controller.borrowBook();
        } else if (choice == 5) {
            controller.returnBook();
        } else if (choice == 6) {
            controller.displayMyBooks();
        } else if (choice == 7) {
            controller.addBook();
        } else if (choice == 8) {
            controller.logout();
            System.out.println("Logged out successfully!");
            return;
        } else {
            System.out.println("Invalid choice! Please try again.");
        }
        
        if (choice != 8) {
            InputUtils.pressAnyKeyToContinue();
        }
    }
    
    private void login() {
        System.out.println("\n=== LOGIN ===");
        String username = InputUtils.getString("Enter username: ");
        String password = InputUtils.getString("Enter password: ");
        
        int result = controller.login(username, password);
        if (result == 1) {
            System.out.println("Login successful! Welcome " + username + "!");
        } else {
            System.out.println("Login failed! Invalid username or password.");
        }
    }
    
    private void register() {
        System.out.println("\n=== REGISTER ===");
        String username = InputUtils.getString("Enter username: ");
        String password = InputUtils.getString("Enter password: ");
        String name = InputUtils.getString("Enter your full name: ");
        String email = InputUtils.getString("Enter your email: ");
        
        int result = controller.register(username, password, name, email);
        if (result == 1) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed! Username already exists.");
        }
    }
}