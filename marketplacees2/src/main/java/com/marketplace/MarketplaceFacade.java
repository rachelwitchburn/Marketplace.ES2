package com.marketplace;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Buyer;
import com.marketplace.model.Product;
import com.marketplace.model.Store;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MarketplaceFacade {
    private List<Buyer> buyers;
    private List<Product> products;
    private List<Store> stores;

    private static final String BUYERS_FILE = "buyers.dat";
    private static final String PRODUCTS_FILE = "products.dat";
    private static final String STORES_FILE = "stores.dat";

    public MarketplaceFacade() {
        this.buyers = loadData(BUYERS_FILE);
        this.products = loadData(PRODUCTS_FILE);
        this.stores = loadData(STORES_FILE);
    }

    // Adicionar comprador
    public void addBuyer(String name, String email, String password, String cpf, String address) {
        Buyer buyer = new Buyer(name, email, password, cpf, address);
        buyers.add(buyer);
        saveData(BUYERS_FILE, buyers);
    }

    // Adicionar produto
    public void addProduct(String name, float value, ProductType type, String brand, String description) {
        Product product = new Product(name, value, type, brand, description);
        products.add(product);
        saveData(PRODUCTS_FILE, products);
    }

    // Adicionar loja
    public void addStore(String name, String email, String password, String cnpj, String address) {
        Store store = new Store(name, email, password,  cnpj,  address);
        stores.add(store);
        saveData(STORES_FILE, stores);
    }

    // Listar compradores
    public List<Buyer> listBuyers() {
        return buyers;
    }

    // Listar produtos
    public List<Product> listProducts() {
        return products;
    }

    // Listar lojas
    public List<Store> listStores() {
        return stores;
    }

    // Atualizar comprador
    public void updateBuyer(int id,String email, String name, String password, String cpf, String address) {
        for (Buyer buyer : buyers) {
            if (buyer.getId() == id) {
                buyer.setName(name);
                buyer.setPassword(password);
                buyer.setCpf(cpf);
                buyer.setAddress(address);
                saveData(BUYERS_FILE, buyers);
                return;
            }
        }
    }

    // Atualizar produto
    public void updateProduct(String productName, double value, ProductType type, String brand, String description) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.setValue(value);
                product.setType(type);
                product.setBrand(brand);
                product.setDescription(description);
                saveData(PRODUCTS_FILE, products);
                return;
            }
        }
    }

    // Atualizar loja
    public void updateStore(int id, String name, String email, String password, String cnpj, String address) {
        for (Store store : stores) {
            if (store.getId() == id) {
                store.setName(name);
                store.setEmail(email);
                store.setPassword(password);
                store.setCnpj(cnpj);
                store.setAddress(address);
                saveData(STORES_FILE, stores);
                return;
            }
        }
    }


    // Deletar comprador
    public void deleteBuyer(String email) {
        buyers.removeIf(buyer -> buyer.getEmail().equals(email));
        saveData(BUYERS_FILE, buyers);
    }

    // Deletar produto
    public void deleteProduct(String productName) {
        products.removeIf(product -> product.getName().equals(productName));
        saveData(PRODUCTS_FILE, products);
    }

    // Deletar loja
    public void deleteStore(String storeName) {
        stores.removeIf(store -> store.getName().equals(storeName));
        saveData(STORES_FILE, stores);
    }

    // Método genérico para salvar listas
    private <T> void saveData(String fileName, List<T> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método genérico para carregar listas
    @SuppressWarnings("unchecked")
    private <T> List<T> loadData(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
