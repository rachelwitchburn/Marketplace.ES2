package com.marketplace.model;

import java.io.Serializable;

import com.marketplace.Enum.ProductType;

public class Product implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private double value;
    private ProductType type;
    private String brand;
    private String description;


    public Product(String name, double value, ProductType type, String brand, String description) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.brand = brand;
        this.description = description;
    }

    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public double getValue() {
        return value; 
    }

    public void setValue(double value) {
        this.value = value; 
    }

    public ProductType getType() { 
        return type; 
    }

    public void setType(ProductType type) { 
        this.type = type; 
    }

    public String getBrand() { 
        return brand; 
    }

    public void setBrand(String brand) { 
        this.brand = brand; 
    }

    public String getDescription() { 
        return description; 
    }

    public void setDescription(String description) {
        this.description = description; 
    }

    @Override
    public String toString() {
        return "Product{" +
               "nome='" + name + '\'' +
               ", valor='" + value + '\'' +
               ", categoria='" + type + '\'' +
               ", marca='" + brand + '\'' +
               ", descrição='" + description + '\'' +
               '}';
    }
}

