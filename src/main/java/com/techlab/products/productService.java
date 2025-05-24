package com.techlab.products;

import com.techlab.utils.utils;

import java.util.ArrayList;
import java.util.Scanner;

public class productService {
    private static final ArrayList<products> items = new ArrayList<>();

    public productService() {


    }

    public static void productRemove() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el ID del producto a eliminar:");
        int idToRemove = teclado.nextInt();
        products productToRemove = productSearchByID(idToRemove);
        if (productToRemove == null) {
            System.out.println("Ningun producto con ID" + " '" + idToRemove + "'" + " encontrado.");
            return;
        }

        teclado.nextLine();

        productToRemove.showInfo();
        System.out.println("esta seguro de eliminar el producto? (S/N): ");

        String response = teclado.nextLine();

        if (response.equalsIgnoreCase("s")) {
            System.out.println("borrando: " + productToRemove.getName() + "...");
            boolean deleted = items.removeIf(item -> item.getId() == idToRemove);

            if (deleted) {
                System.out.println("Borrado correctamente");
            } else {
                System.out.println("hubo un problema al eliminar el producto");
            }

        }

        return;

    }

    public static void itemSearch() {
        utils.showTitle("Buscar Producto");
        System.out.println("Ingrese lo que quiere buscar:");
        int found = 0;
        Scanner teclado = new Scanner(System.in);
        String search = teclado.nextLine();
        System.out.println("buscando: " + search + "...");

        for (products item : items) {
            if (item.CheckIfContains(search)) {
                found++;
                item.showInfo();
            }

        }
        System.out.println("Total encontrados: [" + found + "]");

    }

    public static void listItems() {
        utils.showTitle("Listar Items");


        if (items.isEmpty()) {
            System.out.println("No hay productos registrados");
        } else {
            for (products producto : items) {
                producto.showInfo();

            }
            System.out.println("Total " + items.size() + " productos");
        }
    }

    public static void addProduct() {
        Scanner teclado = new Scanner(System.in);
        String opcion;

        do {
            utils.showTitle("Agregar Producto");

            System.out.println("nombre del producto:");
            String name = teclado.nextLine();

            System.out.printf("Ingrese el precio de %s:\n", name);
            double price = teclado.nextDouble();

            System.out.printf("Ingrese el stock de %s:\n", name);
            int stock = teclado.nextInt();

            teclado.nextLine();

            items.add(new products(name, price, stock));
            System.out.println("Producto cargado exitosamente!");

            System.out.print("¿Desea agregar otro producto? (S/N): ");
            opcion = teclado.nextLine().trim().toUpperCase();

        } while (opcion.equals("S"));
    }

    public static products productSearchByID(int id ) {
        for (products item : items) {
            if (item.getId() == id) {
                return item;

            }

        }
        return null;
    }

    public static products productSearchByNameOrID(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();

            for (products item : productService.items) {
                if (item.getId() == id) return item;
            }
        } else {
            String name = scanner.nextLine();
            for (products item : productService.items) {
                if (item.getName().equalsIgnoreCase(name)) return item;
            }
        }
        return null;
    }


    public static ArrayList <products> getItems() {
        return items;
    }


}
