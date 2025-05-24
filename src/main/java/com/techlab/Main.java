package com.techlab;

import com.techlab.orders.orderService;
import com.techlab.products.productService;
import com.techlab.products.products;
import com.techlab.utils.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        int menuSelected = -1;

        Scanner teclado = new Scanner(System.in);

        ArrayList<products> items = productService.getItems();

        items.add(new products("Papás", 1005, 2));
        items.add(new products("Queso", 2000.05, 45));
        items.add(new products("Arroz", 150.20, 5));
        items.add(new products("Salchichas", 109.50, 10));

        do {
            utils.showMenu("Menu Principal", new String[]{"1) Agregar Productos", "2) Listar Productos", "3) Buscar producto", "4) Eliminar Producto", "5) Crear pedido", "6) Listar Pedidos", "7) Salir"});

            try {
                menuSelected = teclado.nextInt();
                teclado.nextLine();

                switch (menuSelected) {
                    case 1 -> productService.addProduct();
                    case 2 -> productService.listItems();
                    case 3 -> productService.itemSearch();
                    case 4 -> productService.productRemove();
                    case 5 -> orderService.createOrder();
                    case 6 -> orderService.listOrders();
                    case 7 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("⚠ Opción inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠ Debe ingresar un número válido.\nUsted ingreso: '" + teclado.nextLine() + "'\nOpciones validas 1-7" );

                continue;
            }

        } while (menuSelected != 7);
    }

}
