package com.techlab.utils;

import com.techlab.orders.orders;
import com.techlab.products.products;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class utils {
    ArrayList<orders> allOrders = new ArrayList<>();

    public static void showTitle(String titulo){
         showMenu(titulo, new String[]{});
    }

    public static void showMenu(String titulo, String[] opciones) {
        String border = "═".repeat(31);

        System.out.println("\n╔" + border + "╗");
        System.out.printf("║ %-29s ║\n", titulo);
        if (opciones.length == 0) {
            System.out.println("╚" + border + "╝");

        } else {
            System.out.println("╠" + border + "╣");
            for (String opcion : opciones) {
                System.out.printf("║ %-29s ║\n", opcion);
            }

            System.out.println("╚" + border + "╝");
            System.out.print("Seleccione una opción: ");
        }
    }

    public static void printTable(String[] labels, String[] values) {
        final String border = "-".repeat(30);

        System.out.println(border);

        for (int i = 0; i < labels.length; i++) {
            System.out.printf("| %-6s: %-20s |\n", labels[i], values[i]);
        }

        System.out.println(border);
    }


    public static int ingresarCantidad(Scanner scanner, products product) {
        scanner.nextLine();
        System.out.printf("Ingrese la cantidad para '%s' (Stock disponible: %d):\n",
                product.getName(), product.getStock());

        int cantidad;
        try {
            cantidad = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("❌ Entrada inválida.");
            return -1;
        }

        if (cantidad <= 0 || cantidad > product.getStock()) {
            System.out.println("❌ Cantidad inválida.");
            return -1;
        }

        return cantidad;
    }



}
