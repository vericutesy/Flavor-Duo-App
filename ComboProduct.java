package com.example.flavorduoapp.Domain;

public class ComboProduct {
    private String title;
    private String subtitle;
    private String price;
    private int imageResId;

    // Constructor
    public ComboProduct(String title, String subtitle, String price, int imageResId) {
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getters
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }

    public int getName() {
        return 0;
    }
}
