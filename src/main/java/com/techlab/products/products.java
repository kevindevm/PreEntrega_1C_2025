package com.techlab.products;

import com.techlab.exceptions.NoStockException;
import com.techlab.utils.utils;

import java.text.Normalizer;


public class products {
    private int stock;
    private final int id;
    private double price;
    private String name;
    private static int nextId = 1;


    public products(String name, double price, int stock) {
        this.name = returnCapitalized(name);
        this.price = price;
        this.stock = stock;
        this.id = nextId;
        nextId++;
    }

    public boolean CheckIfContains(String str) {
        String nameNormalized = Normalizer.normalize(str.toLowerCase(), Normalizer.Form.NFD);
        String nameLowercase = this.name.toLowerCase();
        return nameLowercase.contains(nameNormalized);
    }

    public void showInfo() {
        utils.printTable(new String[]{"ID", "Nombre", "Precio", "Stock"}, new String[]{String.valueOf(this.id), this.name, String.valueOf(this.price), String.valueOf(this.stock)});

    }


    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void updateName(String nombre) {
        this.name = nombre;
    }

    // ==== price ====

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("El precio no puede ser negativo");
            return;
        }
        this.price = price;
    }


    // ==== STOCK ====

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {

        this.stock = stock;
    }

    public void updateStock(int amount) throws NoStockException {
        if (this.stock + amount < 0) {
            throw new NoStockException("No hay suficiente stock para el producto con ID: " + this.id);
        }

        this.stock += amount;
    }


    private static String returnCapitalized(String arg) {

        String resultado = arg.toLowerCase().trim();
        String[] partes = resultado.replaceAll(" +", " ").split(" ");
        StringBuilder NewCadena = new StringBuilder(resultado.length());

        for (String parteActual : partes) {
            NewCadena.append(parteActual.toUpperCase().charAt(0)).append(parteActual.substring(1)).append(" ");
        }
        return NewCadena.toString().trim();


    }


}
