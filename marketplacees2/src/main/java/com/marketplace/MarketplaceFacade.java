package com.marketplace;

import com.marketplace.model.Buyer;
import com.marketplace.model.Store;
import com.marketplace.model.Product;
import com.marketplace.service.BuyerService;
import com.marketplace.service.StoreService;
import com.marketplace.service.ProductService;

import java.util.List;

public class MarketplaceFacade {
    private final BuyerService buyerService;
    private final StoreService storeService;
    private final ProductService productService;

    public MarketplaceFacade(BuyerService buyerService, StoreService storeService, ProductService productService) {
        this.buyerService = buyerService;
        this.storeService = storeService;
        this.productService = productService;
    }

    // Gerenciamento de Compradores
    public void addBuyer(String name, String email, String password, String cpf, String address) {
        Buyer newBuyer = new Buyer(name, email, password, cpf, address);
        buyerService.addBuyer(newBuyer);
    }

    public List<Buyer> listBuyers() { return buyerService.listBuyers(); }

    public boolean updateBuyer(int id, String name, String email, String password, String cpf, String address) {
        Buyer updatedBuyer = new Buyer(name, email, password, cpf, address);
        updatedBuyer.setId(id);
        return buyerService.updateBuyer(updatedBuyer);
    }

    public boolean removeBuyer(int id) { return buyerService.removeBuyer(id); }

    // Gerenciamento de Lojas
    public void addStore(String name, String email, String password, String cnpj, String address) {
        Store newStore = new Store(name, email, password, cnpj, address);
        storeService.addStore(newStore);
    }

    public List<Store> listStores() { return storeService.listStores(); }

    public boolean updateStore(int id, String name, String email, String password, String cnpj, String address) {
        Store updatedStore = new Store(name, email, password, cnpj, address);
        updatedStore.setId(id);
        return storeService.updateStore(updatedStore);
    }

    public boolean removeStore(int id) { return storeService.removeStore(id); }

    // Gerenciamento de Produtos
    public void addProduct(String name, double value, int quantity, int storeId) {
        Product newProduct = new Product();
        productService.addProduct(newProduct);
    }

    public List<Product> listProducts() { return productService.listProducts(); }

    public boolean updateProduct(int id, String name, double price, int quantity, int storeId) {
        Product updatedProduct = new Product();
        updatedProduct.setId(id);
        return productService.updateProduct(updatedProduct);
    }

    public boolean removeProduct(int id) { return productService.removeProduto(id); }
}

