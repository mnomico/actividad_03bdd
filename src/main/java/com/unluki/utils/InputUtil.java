package com.unluki.utils;

import java.util.Scanner;

public class InputUtil {

    public static int leerEntero(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return -1;
                }
                return Integer.parseInt(input);
            } catch ( NumberFormatException e ) {
                System.out.println("Por favor, ingrese un número válido o presione Enter para ver todo.");
            }
        }
    }
}
