package com.techlab.orders;

import com.techlab.exceptions.NoStockException;
import com.techlab.products.products;

import java.util.ArrayList;
import java.util.List;

class OrderItem {
    private final products product;
    private int quantity;

    public OrderItem(products product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public products getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}


public class orders {
    private final int id;
    private static int nextId = 1;
    private ArrayList<OrderItem> cart = new ArrayList<>();


    public orders(products product, int quantity) {
        this();
        this.cart = new ArrayList<>();
        try {
            product.updateStock(-quantity);
        } catch (NoStockException e) {
            throw new NoStockException("No hay stock suficiente para el producto con ID: " + product.getId());
        }


        this.cart.add(new OrderItem(product, quantity));

    }

    public orders() {
        this.id = nextId;
        nextId++;
    }

    public void addItem(products product, int quantity) {
        for (OrderItem item : this.cart) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Si no estaba, lo agregamos como nuevo
        this.cart.add(new OrderItem(product, quantity));
    }


    public void removeItem() {
    }

    public int getID() {
        return this.id;
    }


    public String removeItemByID(int ID) {
        boolean removed = this.cart.removeIf(item -> item.getProduct().getId() == ID);
        return removed ? "✔ Eliminado correctamente" : "✖ Error: Ningún producto con ese ID";
    }


    public String removeItemByName(String name) {
        boolean removed = this.cart.removeIf(item -> item.getProduct().getName().equalsIgnoreCase(name));
        return removed ? "✔ Eliminado correctamente" : "✖ Error: Ningún producto con ese nombre";
    }


    public double getTotal() {
        double total = 0;
        for (OrderItem item : this.cart) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<OrderItem> getCart() {
        return this.cart;
    }


    // by chat gpt jaja
    public static void showCart(int orderId, List<OrderItem> cart) {
        double total = 0;

        // Encabezados
        String[] headers = {"Cant", "Producto", "P/U", "Subtotal"};

        // Calcular anchos máximos
        int lenCant = headers[0].length();
        int lenProd = headers[1].length();
        int lenUnit = headers[2].length();
        int lenSubt = headers[3].length();

        for (OrderItem item : cart) {
            lenCant = Math.max(lenCant, String.valueOf(item.getQuantity()).length());
            lenProd = Math.max(lenProd, item.getProduct().getName().length());
            lenUnit = Math.max(lenUnit, String.format("$%.2f", item.getProduct().getPrice()).length());
            double subtotal = item.getQuantity() * item.getProduct().getPrice();
            lenSubt = Math.max(lenSubt, String.format("$%.2f", subtotal).length());
        }

        // Separadores
        String separator = "+" +
                "-".repeat(lenCant + 2) + "+" +
                "-".repeat(lenProd + 2) + "+" +
                "-".repeat(lenUnit + 2) + "+" +
                "-".repeat(lenSubt + 2) + "+";

        // Mostrar encabezado
        System.out.println("ORDEN ID: " + orderId);
        System.out.println(separator);
        System.out.printf("| %" + lenCant + "s | %-" + lenProd + "s | %" + lenUnit + "s | %" + lenSubt + "s |\n",
                headers[0], headers[1], headers[2], headers[3]);
        System.out.println(separator);

        // Mostrar productos
        for (OrderItem item : cart) {
            int qty = item.getQuantity();
            String name = item.getProduct().getName();
            double unitPrice = item.getProduct().getPrice();
            double subtotal = qty * unitPrice;
            total += subtotal;

            System.out.printf("| %" + lenCant + "d | %-" + lenProd + "s | %" + lenUnit + "s | %" + lenSubt + "s |\n",
                    qty, name, String.format("$%.2f", unitPrice), String.format("$%.2f", subtotal));
        }

        // Pie de tabla
        System.out.println(separator);
        System.out.printf("%" + (lenCant + lenProd + lenUnit + lenSubt + 13) + "s\n", "TOTAL: $" + String.format("%,.2f", total));
    }


}