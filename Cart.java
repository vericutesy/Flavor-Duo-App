package com.example.flavorduoapp.Domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<CartItem> items;

    private Cart() {
        items = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    // Add item with merging logic
    public void addItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.getTitle().equals(newItem.getTitle())) {
                // Increase quantity if same product
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return; // exit method, do not add new item
            }
        }
        // If not found, add new item
        items.add(newItem);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
