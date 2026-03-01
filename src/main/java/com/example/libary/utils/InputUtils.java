package com.example.library.utils;

import java.util.Scanner;

public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
    
    public static void pressAnyKeyToContinue() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}