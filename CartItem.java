// CartItem.java
package com.example.flavorduoapp.Domain;

public class CartItem {
    private String title;
    private double price;
    private int imageResId;
    private int quantity; // new field

    // Updated constructor with quantity
    public CartItem(String title, double price, int imageResId, int quantity) {
        this.title = title;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = quantity;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Optional: setter if you want to change quantity later
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
