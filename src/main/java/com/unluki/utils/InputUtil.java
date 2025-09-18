package com.unluki.utils;

import java.util.Scanner;

public class InputUtil {

    public static int leerEntero(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch ( NumberFormatException e ) {
            return -1;
        }
    }
}
