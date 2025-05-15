package com.marketplace.model;

import java.io.Serializable;

import com.marketplace.Enum.ProductType;

public class Product implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double value;
    private int quantity;
    private ProductType type;
    private String brand;
    private String description;


    public Product(String name, double value, int quantity, ProductType type, String brand, String description) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.type = type;
        this.brand = brand;
        this.description = description;
    }

    public int getId() { 
        return this.id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return this.name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public double getValue() {
        return this.value; 
    }

    public void setValue(double value) {
        this.value = value; 
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getType() { 
        return this.type; 
    }

    public void setType(ProductType type) { 
        this.type = type; 
    }

    public String getBrand() { 
        return this.brand; 
    }

    public void setBrand(String brand) { 
        this.brand = brand; 
    }

    public String getDescription() { 
        return this.description; 
    }

    public void setDescription(String description) {
        this.description = description; 
    }

    @Override
    public String toString() {
        return "Product{" +
               "nome='" + name + '\'' +
               ", valor='" + value + '\'' +
               ", quantidade='" + quantity + '\'' +
               ", categoria='" + type + '\'' +
               ", marca='" + brand + '\'' +
               ", descrição='" + description + '\'' +
               '}';
    }
}

