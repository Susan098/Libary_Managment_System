package com.example.library.utils;

import java.util.Scanner;

public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    public static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public static String getStringInput() {
        return scanner.nextLine();
    }
    
    public static void close() {
        scanner.close();
    }
}