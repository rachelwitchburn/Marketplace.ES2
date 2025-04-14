package com.marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Buyer implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String address;
    private transient List<Product> cart = new ArrayList<>();

    public Buyer() {

    }

    public Buyer(String name, String email, String password, String cpf, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.address = address;
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

    public String getCpf() { 
        return cpf; 
    }

    public void setCpf(String cpf) { 
        this.cpf = cpf; 
    }

    public String getAddress() { 
        return address; 
    }

    public void setAddress(String address) { 
        this.address = address; 
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        this.cart.add(product);
    }

    public void deleteFromCart(Product product){this.cart.remove(product); }

    public void cleanCart() {
        this.cart.clear();
    }

    @Override
    public String toString() {
        return "Buyer{" +
               "nome='" + name + '\'' +
               ", email='" + email + '\'' +
               ", cpf='" + cpf + '\'' +
               ", endereco='" + address + '\'' +
               '}';
    }
}

