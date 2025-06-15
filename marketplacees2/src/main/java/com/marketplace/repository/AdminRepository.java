package com.marketplace.repository;

import com.marketplace.model.Admin;
// import com.marketplace.model.Buyer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static final String FILE_PATH = "admins.ser";

    public List<Admin> loadAdmins() {
        List<Admin> admins = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> tempList = (List<?>) obj;
                for (Object item : tempList) { 
                    if (item instanceof Admin) {
                        admins.add((Admin) item);
                } else {
                    throw new ClassCastException("Objeto inválido encontrado na lista");
                }
            } 
        } else {
            throw new ClassCastException("Objeto deserializado não é uma List");
        }
    } catch (IOException | ClassNotFoundException | ClassCastException e) {
        e.printStackTrace();
    }
    return admins;
}
    
    public void saveAdmins(List<Admin> admins) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(admins);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void addAdmin(Admin admin) {
        List<Admin> admins = loadAdmins();
        int maxId = 0;
        for (Admin a : admins) {
            if (a.getId() > maxId) {
                maxId = a.getId();
            }
        }
        admin.setId(maxId + 1);
        admins.add(admin);
        saveAdmins(admins);
    }
    
    public List<Admin> getAllAdmins() {
        return loadAdmins();
    }
    
    public boolean updateAdmin(Admin updatedAdmin) {
        List<Admin> admins = loadAdmins();
        boolean found = false;
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId() == updatedAdmin.getId()) {
                admins.set(i, updatedAdmin);
                found = true;
                break;
            }
        }
        if (found) {
            saveAdmins(admins);
        }
        return found;
    }
    
    public boolean removeAdmin(int id) {
        List<Admin> admins = loadAdmins();
        boolean removed = admins.removeIf(a -> a.getId() == id);
        if (removed) {
            saveAdmins(admins);
        }
        return removed;
    }
}
