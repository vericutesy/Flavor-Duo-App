package com.example.flavorduoapp.Domain;

public class CupcakeProduct {

    private String title;
    private String subtitle;   // used as description
    private String price;
    private int imageResId;

    public CupcakeProduct(String title, String subtitle, String price, int imageResId) {
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
