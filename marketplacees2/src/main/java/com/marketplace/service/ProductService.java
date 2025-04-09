package com.marketplace.service;

import com.marketplace.model.Product;
import com.marketplace.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductRepository repository = new ProductRepository();

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    
    public void addProduct(Product product) {
        repository.addProduct(product);
    }
    
    public List<Product> listProducts() {
        return repository.getAllProducts();
    }
    
    public boolean updateProduct(Product product) {
        return repository.updateProduct(product);
    }
    
    public boolean removeProduct(int id) {
        return repository.removeProduct(id);
    }

    public List<Product> searchProduct(String name) {
        List<Product> all = repository.getAllProducts();
        List<Product> found = new ArrayList<>();

        for (Product p : all) {
            if (p.getName().equalsIgnoreCase(name)) {
                found.add(p);
            }
        }

        return found;
    }
}
