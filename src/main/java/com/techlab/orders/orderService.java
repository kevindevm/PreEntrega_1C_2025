package com.techlab.orders;

import com.techlab.exceptions.NoStockException;
import com.techlab.products.productService;
import com.techlab.products.products;
import com.techlab.utils.utils;

import java.util.ArrayList;
import java.util.Scanner;

public class orderService {
    static ArrayList<orders> allOrders = new ArrayList<>();

    public ArrayList<orders> getAllOrders() {
        return allOrders;
    }

     public static void listOrders() {
        utils.showTitle("Listar Pedidos");
        if (allOrders.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (orders o : allOrders) {
//            System.out.println("\n🧾 Pedido ID: " + o.getID());
            orders.showCart(o.getID(),o.getCart());
        }
    }

    public static void createOrder(ArrayList<products> items) throws NoStockException {
         utils.showTitle("Crear Pedido");
        Scanner scanner = new Scanner(System.in);
        orders currentOrder = null;
        currentOrder = new orders();
        allOrders.add(currentOrder);
        System.out.println("Nuevo pedido creado con ID: " + currentOrder.getID());


        boolean keepOnAdding = true;

        while (keepOnAdding) {
            System.out.println("Ingrese el ID o nombre del producto a agregar a la orden:");
            products selectedProduct = productService.productSearchByNameOrID(scanner);
            if (selectedProduct == null) {
                System.out.println("❌ Producto no encontrado.");
                continue;
            }
            if (selectedProduct.getStock() <= 0) {
                System.out.println("❌ Producto sin stock");
                return;
            }

            int cantidad = utils.ingresarCantidad(scanner, selectedProduct);
            if (cantidad == -1) continue;

            currentOrder.addItem(selectedProduct, cantidad);
            try {
                selectedProduct.updateStock(-cantidad);
            }catch (NoStockException e){
                throw new NoStockException("No hay stock suficiente para el producto con ID: " + selectedProduct.getId());
            }

            System.out.println("✅ Producto agregado al carrito ID " + currentOrder.getID());

            System.out.println("¿Desea agregar otro producto? (s/n):");
            keepOnAdding = scanner.nextLine().equalsIgnoreCase("s");
        }
    }





}
