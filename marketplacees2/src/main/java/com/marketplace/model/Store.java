package com.marketplace.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Store implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;
    private String password;
    private String cnpj;
    private String address;
    private Map<Buyer, Integer> ratings = new HashMap<>();
    private Map<Buyer, String> comments = new HashMap<>();

    public Store() {

    }

    public Store(String name, String email, String password, String cnpj, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnpj = cnpj;
        this.address = address;
        this.ratings = new HashMap<>();
        this.comments = new HashMap<>();
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

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPassword() { 
        return password; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getCnpj() { 
        return cnpj; 
    }

    public void setCnpj(String cnpj) { 
        this.cnpj = cnpj; 
    }

    public String getAddress() { 
        return address; 
    }

    public void setAddress(String address) { 
        this.address = address; 
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
        if (ratings.isEmpty()) return 0.0;
        return ratings.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return "Store{" +
               "nome='" + name + '\'' +
               ", email='" + email + '\'' +
               ", cnpj='" + cnpj + '\'' +
               ", endereco='" + address + '\'' +
               '}';
    }
}

