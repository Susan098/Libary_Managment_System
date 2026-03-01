package com.example.libary.controller;

import com.example.libary.utils.InputUtils;

public class LibraryMenu {

    public void start() {
        while (true) {
            System.out.println("\n--- Library System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = InputUtils.readInt("Choose option: ");

            switch (choice) {
                case 1 -> new Register().register();
                case 2 -> new Login().login();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
