package com.marketplace;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Admin;
import com.marketplace.model.Buyer;
import com.marketplace.model.Product;
import com.marketplace.model.Store;

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

        for (Buyer buyer : this.buyers) {
            if (buyer.getCart() == null) {
                buyer.setCart(new ArrayList<>());
            }
        }


        if (this.admins == null || this.admins.isEmpty()) {
            Admin defaultAdmin = new Admin("Administrador", "admin@admin.com", "admin123", "00000000000", "N/A");
            this.admins.add(defaultAdmin);
            saveData(ADMINS_FILE, admins);
        }
    }

    public void addBuyer(String name, String email, String password, String cpf, String address) {
        Buyer buyer = new Buyer(name, email, password, cpf, address);
        buyers.add(buyer);
        saveData(BUYERS_FILE, buyers);
    }

    public void addProduct(String name, double value, int quantity, ProductType type, String brand, String description, Store store) {
        Product product = new Product(name, value, quantity, type, brand, description, store);
        products.add(product);
        saveData(PRODUCTS_FILE, products);
    }

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

    public List<Buyer> listBuyers() {
        return buyers;
    }

    public List<Product> listProducts() {
        return products;
    }

    public List<Product> searchProduct(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public Product getProductByName(String name) {
        return products.stream()
                       .filter(p -> p.getName().equalsIgnoreCase(name))
                       .findFirst()
                       .orElse(null);
    }

    public List<Store> listStores() {
        return stores;
    }

    public List<Admin> listAdmins() {
        return admins;
    }

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

    public void deleteBuyer(String email) {
        buyers.removeIf(buyer -> buyer.getEmail().equals(email));
        saveData(BUYERS_FILE, buyers);
    }

    public void deleteProduct(String productName) {
        products.removeIf(product -> product.getName().equals(productName));
        saveData(PRODUCTS_FILE, products);
    }

    public void deleteStore(String storeName) {
        stores.removeIf(store -> store.getName().equals(storeName));
        saveData(STORES_FILE, stores);
    }

    public void deleteAdmin(String email) {
        admins.removeIf(admin -> admin.getEmail().equals(email));
        saveData(ADMINS_FILE, buyers);
    }

    private <T> void saveData(String fileName, List<T> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAllData() {
        saveData(BUYERS_FILE, buyers);
        saveData(PRODUCTS_FILE, products);
        saveData(STORES_FILE, stores);
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

    public boolean buyProduct(Buyer buyer, String productName, int discount) {
        for (Product product : this.products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return buyer.buyProduct(product, discount);
            }
        }
        System.out.println("Produto não encontrado.");
        return false;
    }

    public boolean finalizePurchase(Buyer buyer, int discount) {
        return buyer.finalizePurchase(discount);
    }

    public boolean rateProduct(Buyer buyer, String productName, int rating, String comment) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5.");
        }

        for (Product p : buyer.getPurchaseHistory()) {
            if (p.getName().equalsIgnoreCase(productName)) {
                Product productInMainList = getProductByName(productName);
                if (productInMainList != null) {
                    productInMainList.addRating(buyer, rating);
                    productInMainList.addComment(buyer, comment);
                    saveAllData();
                    return true;
                }   
            }
        }
        return false;
    }

    public boolean rateStore(Buyer buyer, String storeName, int rating, String comment) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5.");
        }

        for (Product p : buyer.getPurchaseHistory()) {
            Store store = p.getStore();
            if (store != null && store.getName().equalsIgnoreCase(storeName)) {
                store.getRatings().put(buyer, rating);
                store.getComments().put(buyer, comment);
                return true;
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

    public void getPurchaseHistory(Buyer user) {
        System.out.println("Histórico de compras:");
        for (Product product : user.getPurchaseHistory()) {
            System.out.println(product.getName());
        }
    }
}
