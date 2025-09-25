package com.example.flavorduoapp.Domain;

public class DrinkProduct {

    private String title;
    private String price;
    private String description;
    private int imageResId;

    public DrinkProduct(String title, String price, String description, int imageResId) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
