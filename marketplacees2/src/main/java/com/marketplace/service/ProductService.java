package com.marketplace.service;

import com.marketplace.model.Product;
import com.marketplace.repository.ProductRepository;
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
    
    public boolean removeProduto(int id) {
        return repository.removeProduct(id);
    }
}
