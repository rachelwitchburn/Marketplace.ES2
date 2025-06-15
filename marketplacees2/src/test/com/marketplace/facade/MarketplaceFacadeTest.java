package com.marketplace.facade;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Admin;
import com.marketplace.model.Buyer;
import com.marketplace.model.Product;
import com.marketplace.model.Store;
import com.marketplace.repository.AdminRepository;
import com.marketplace.repository.BuyerRepository;
import com.marketplace.repository.StoreRepository;
import com.marketplace.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MarketplaceFacadeTest {
    private MarketplaceFacade marketplaceFacadeTest;

    @BeforeEach
    void setUp() {
        // Injeta o mock no serviço
        StoreRepository storeRepository = mock(StoreRepository.class);
        AdminRepository adminRepository = mock(AdminRepository.class);
        BuyerRepository buyerRepository = mock(BuyerRepository.class);

        marketplaceFacadeTest = new MarketplaceFacade();
    }
    @Test
    void addBuyer() {
        marketplaceFacadeTest.addBuyer("Matheus","matheus123@gmail.com","senha123","12345678910","Rua Teste");

        Object retorno = marketplaceFacadeTest.login("matheus123@gmail.com", "senha123");
        assertNotNull(retorno);
        assertTrue(retorno instanceof Buyer);
    }

    @Test
    void addProduct() {
        Store loja = new Store();
        marketplaceFacadeTest.addProduct("Nintendo Switch 2", 4999,10, ProductType.VIDEO_GAME, "Nintendo", "Console Portátil",loja);

        Product prod = marketplaceFacadeTest.getProductByName("Nintendo Switch 2");

        assertNotNull(prod);
    }

    @Test
    void addStore() {
        marketplaceFacadeTest.addStore("Loja Teste","lojateste@hotmail.com","senhadaloja123","123456789023","Rua do Teste");

        Object retorno = marketplaceFacadeTest.login("lojateste@hotmail.com", "senhadaloja123");
        assertNotNull(retorno);
        assertTrue(retorno instanceof Store);
    }

    @Test
    void addAdmin() {
        marketplaceFacadeTest.addAdmin("Wagner","wagner123@gmail.com","senha123","12345678910","Rua Teste");

        Object retorno = marketplaceFacadeTest.login("wagner123@gmail.com", "senha123");
        assertNotNull(retorno);
        assertTrue(retorno instanceof Admin);
    }

    @Test
    void listBuyers() {
    }

    @Test
    void listProducts() {
    }

    @Test
    void searchProduct() {
    }

    @Test
    void getProductByName() {
    }

    @Test
    void listStores() {
    }

    @Test
    void listAdmins() {
    }

    @Test
    void updateBuyer() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void updateStore() {
    }

    @Test
    void updateAdmin() {
    }

    @Test
    void deleteBuyer() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void deleteStore() {
    }

    @Test
    void deleteAdmin() {
    }

    @Test
    void saveAllData() {
    }

    @Test
    void addToCart() {
    }

    @Test
    void deleteFromCart() {
    }

    @Test
    void buyProduct() {
    }

    @Test
    void finalizePurchase() {
    }

    @Test
    void rateProduct() {
    }

    @Test
    void rateStore() {
    }

    @Test
    void login() {
    }

    @Test
    void getPurchaseHistory() {
    }
}