package com.marketplace;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Admin;
import com.marketplace.model.Buyer;
import com.marketplace.model.Product;
import com.marketplace.model.Store;
import com.marketplace.service.ProductService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarketplaceFacade {
    private List<Buyer> buyers;
    private List<Product> products;
    private List<Store> stores;
    private List<Admin> admins;

    private static final String BUYERS_FILE = "buyers.dat";
    private static final String PRODUCTS_FILE = "products.dat";
    private static final String STORES_FILE = "stores.dat";
    private static final String ADMINS_FILE = "admins.dat";

    public MarketplaceFacade() {
        this.buyers = loadData(BUYERS_FILE);
        this.products = loadData(PRODUCTS_FILE);
        this.stores = loadData(STORES_FILE);
        this.admins = loadData(ADMINS_FILE);

        if (this.admins == null || this.admins.isEmpty()) {
            Admin defaultAdmin = new Admin("Administrador", "admin@admin.com", "admin123", "00000000000", "N/A");
            this.admins.add(defaultAdmin);
            saveData(ADMINS_FILE, admins);
        }
    }

    // Adicionar comprador
    public void addBuyer(String name, String email, String password, String cpf, String address) {
        Buyer buyer = new Buyer(name, email, password, cpf, address);
        buyers.add(buyer);
        saveData(BUYERS_FILE, buyers);
    }

    // Adicionar produto
    public void addProduct(String name, Float value, int quantity, ProductType type, String brand, String description) {
        Product product = new Product(name, value, quantity, type, brand, description);
        products.add(product);
        saveData(PRODUCTS_FILE, products);
    }

    // Adicionar loja
    public void addStore(String name, String email, String password, String cnpj, String address) {
        Store store = new Store(name, email, password,  cnpj,  address);
        stores.add(store);
        saveData(STORES_FILE, stores);
    }

    public void addAdmin(String name, String email, String password, String cpf, String address) {
        Buyer buyer = new Buyer(name, email, password, cpf, address);
        buyers.add(buyer);
        saveData(BUYERS_FILE, buyers);
    }

    // Listar compradores
    public List<Buyer> listBuyers() {
        return buyers;
    }

    // Listar produtos
    public List<Product> listProducts() {
        return products;
    }

    //Buscar produtos
    public List<Product> searchProduct(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    // Listar lojas
    public List<Store> listStores() {
        return stores;
    }

    public List<Admin> listAdmins() {
        return admins;
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
    public void updateProduct(String productName, Float value, int quantity, ProductType type, String brand, String description) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.setValue(value);
                product.setType(type);
                product.setQuantity(quantity);
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

    public void updateAdmin(int id,String email, String name, String password, String cpf, String address) {
        for (Admin admin : admins) {
            if (admin.getId() == id) {
                admin.setName(name);
                admin.setPassword(password);
                admin.setCpf(cpf);
                admin.setAddress(address);
                saveData(ADMINS_FILE, admins);
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

    public void deleteAdmin(String email) {
        admins.removeIf(admin -> admin.getEmail().equals(email));
        saveData(ADMINS_FILE, buyers);
    }

    // Método genérico para salvar listas
    private <T> void saveData(String fileName, List<T> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addToCart(Buyer buyer, String productName) {
        for (Product product : this.products) {
            if (product.getName().equalsIgnoreCase(productName) && product.getQuantity() > 0) {
                buyer.addToCart(product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteFromCart(Buyer buyer, String productName) {
        for (Product product : this.products) {
            if(product.getName().equalsIgnoreCase(productName)){
                buyer.deleteFromCart(product);
            }
        }
        return false;
    }

    public Object login(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
    
        for (Store store : stores) {
            if (store.getEmail().equals(email) && store.getPassword().equals(password)) {
                return store;
            }
        }
    
        for (Buyer buyer : buyers) {
            if (buyer.getEmail().equals(email) && buyer.getPassword().equals(password)) {
                return buyer;
            }
        }
    
        return null;
    }
  

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
