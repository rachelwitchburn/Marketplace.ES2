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
    private List<Product> purchaseHistory = new ArrayList<>();
    private int points;

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

    public List<Product> getPurchaseHistory() {
        if (purchaseHistory == null) {
            purchaseHistory = new ArrayList<>();
        }
        return purchaseHistory;
    }

    public int getPoints(){
        return this.points;
    }

    public void setPoints(int newPoints){
        this.points = newPoints;
    }

    public void addPoints(Product prod){
        this.points += (int)((prod.getValue() * 1)/5.0);
    }

    public void addToCart(Product product) {
        this.cart.add(product);
    }

    public void deleteFromCart(Product product){this.cart.remove(product); }

    public void cleanCart() {
        this.cart.clear();
    }

    public boolean buyProduct(Product product, int discount) {
        if (this.cart.contains(product)) {
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                this.addPoints(product);
                double value = product.getValue();
                this.cart.remove(product);
                System.out.println("Compra realizada com sucesso.");
                System.out.println("Valor Total: R$"+(value-discount));
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

    public boolean finalizePurchase(int discount) {
        boolean atLeastOneBought = false;
        List<Product> purchased = new ArrayList<>();
        int total = 0;

        for (Product product : new ArrayList<>(cart)) {
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                this.addPoints(product);

                purchased.add(product);
                atLeastOneBought = true;
                
                total += product.getValue();

                System.out.println("Comprado: " + product.getName());
            } else {
                System.out.println("Sem estoque: " + product.getName());
            }
        }

        System.out.println("Total da Compra: " + (total-discount));

        cart.removeAll(purchased);
        purchaseHistory.addAll(purchased);
        return atLeastOneBought;
    }

    public String listPurchases() {
        if (purchaseHistory.isEmpty()) {
            return "Nenhuma compra realizada.";
        }
        StringBuilder purchases = new StringBuilder("Histórico de compras: \n");
        for (Product product : cart) {
            purchases.append(product.getName()).append("\n");
        }
        return purchases.toString();
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

