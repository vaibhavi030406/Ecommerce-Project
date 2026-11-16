package com.example.ecommerce.model;

public class Product {
    private final int id;
    private final String name, category, description;
    private final double price;
    private final String image;
    public Product(int id, String name, String category, String description, double price, String image) {
        this.id=id; this.name=name; this.category=category; this.description=description; this.price=price; this.image=image;
    }
    public int getId(){return id;} public String getName(){return name;} public String getCategory(){return category;}
    public String getDescription(){return description;} public double getPrice(){return price;} public String getImage(){return image;}
}
