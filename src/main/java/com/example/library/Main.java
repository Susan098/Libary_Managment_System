package com.example.library;

import com.example.library.controller.LibraryController;
import com.example.library.utils.InputUtils;

public class Main {
    public static void main(String[] args) {
        LibraryController controller = new LibraryController();
        controller.start();
        InputUtils.close();
    }
}