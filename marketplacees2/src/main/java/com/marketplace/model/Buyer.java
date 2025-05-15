package com.marketplace.model;

import com.marketplace.model.Product;
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
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }


    public void addToCart(Product product) {
        this.cart.add(product);
    }

    public void deleteFromCart(Product product){this.cart.remove(product); }

    public void cleanCart() {
        this.cart.clear();
    }

    public boolean buyProduct(Product product) {
        if (this.cart.contains(product)) {
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                this.cart.remove(product);
                System.out.println("Compra realizada com sucesso.");
                return true;
            } else {
                System.out.println("Produto sem estoque.");
                return false;
            }
        } else {
            System.out.println("Produto não está no carrinho.");
            return false;
        }
    }

    public boolean finalizePurchase() {
        boolean atLeastOneBought = false;
        List<Product> purchased = new ArrayList<>();

        for (Product product : new ArrayList<>(cart)) {
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                purchased.add(product);
                atLeastOneBought = true;
                System.out.println("Comprado: " + product.getName());
            } else {
                System.out.println("Sem estoque: " + product.getName());
            }
        }

        cart.removeAll(purchased);
        return atLeastOneBought;
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

