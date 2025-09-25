package com.example.flavorduoapp.Domain;

public class CategoryModel {
    private String name;
    private int id;

    public CategoryModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
