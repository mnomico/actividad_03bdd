package com.unluki.utils;

import java.util.Scanner;

public class InputUtil {

    public static int leerEntero(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch ( NumberFormatException e ) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }
}
