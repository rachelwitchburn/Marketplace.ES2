package com.marketplace.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    private Store store;
    private Map<Buyer, Integer> ratings = new HashMap<>();
    private Map<Buyer, String> comments = new HashMap<>();


    public Product(String name, double value, int quantity, ProductType type, String brand, String description, Store store) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.type = type;
        this.brand = brand;
        this.description = description;
        this.store = store;
        this.ratings = new HashMap<>();
        this.comments = new HashMap<>();
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Map<Buyer, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Map<Buyer, Integer> ratings) {
        this.ratings = ratings;
    }

    public Map<Buyer, String> getComments() {
        return comments;
    }

    public void setComments(Map<Buyer, String> comments) {
        this.comments = comments;
    }

    public void addRating(Buyer buyer, int rating) {
    ratings.put(buyer, rating);
    }

    public void addComment(Buyer buyer, String comment) {
        comments.put(buyer, comment);
    }

    public boolean hasRatingFrom(Buyer buyer) {
        return ratings.containsKey(buyer);
    }

    public double getAverageRating() {
        if (ratings == null){
            ratings = new HashMap<>();
        }
        if (ratings.isEmpty()) return 0.0;
        return ratings.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
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
               ", Avaliação='" + getAverageRating() + '\'' +
               '}';
    }
}

