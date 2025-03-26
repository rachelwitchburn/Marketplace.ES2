package com.marketplace.service;

import com.marketplace.model.Store;
import com.marketplace.repository.StoreRepository;
import java.util.List;

public class StoreService {
    private StoreRepository repository = new StoreRepository();

    public StoreService(StoreRepository repository){
        this.repository = repository;
    }
    
    public void addStore(Store store) {
        repository.addStore(store);
    }
    
    public List<Store> listStores() {
        return repository.getAllStores();
    }
    
    public boolean updateStore(Store store) {
        return repository.updateStore(store);
    }
    
    public boolean removeStore(int id) {
        return repository.removeStore(id);
    }
}
