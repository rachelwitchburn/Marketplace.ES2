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

    //TESTES REQUISITO 7 (AVALIAÇÕES E COMENTÁRIOS
    @Test
    void rateProduct() {
        Buyer  buyer = new Buyer();
        buyer.getPurchaseHistory().add(new Product("Nintendo Switch 2", 4999,10, ProductType.VIDEO_GAME, "Nintendo", "Console Portátil",null));
        assertTrue(marketplaceFacadeTest.rateProduct(buyer,"Nintendo Switch 2", 2, "Veio com defeito"));
    }

    @Test
    void ratingLowerProduct(){
        Buyer  buyer = new Buyer();
        buyer.getPurchaseHistory().add(new Product("Nintendo Switch 2", 4999,10, ProductType.VIDEO_GAME, "Nintendo", "Console Portátil",null));
        assertThrows(IllegalArgumentException.class,() -> marketplaceFacadeTest.rateProduct(buyer,"Nintendo Switch 2", -1, "Veio com defeito"));
    }

    @Test
    void ratingHigherProduct(){
        Buyer  buyer = new Buyer();
        buyer.getPurchaseHistory().add(new Product("Nintendo Switch 2", 4999,10, ProductType.VIDEO_GAME, "Nintendo", "Console Portátil",null));
        assertThrows(IllegalArgumentException.class,() -> marketplaceFacadeTest.rateProduct(buyer,"Nintendo Switch 2",6, "Veio com defeito"));
    }

    @Test
    void rateStore() {
        Buyer  buyer = new Buyer();
        Store store = new Store("Loja Teste","lojateste@hotmail.com","senhadaloja123","123456789023","Rua do Teste");
        buyer.getPurchaseHistory().add(new Product("Nintendo Switch 2", 4999,10, ProductType.VIDEO_GAME, "Nintendo", "Console Portátil",store));
        assertTrue(marketplaceFacadeTest.rateStore(buyer,"Loja Teste", 4, "Boa Loja"));
    }

    @Test
    void ratingLowerStore(){
        Buyer  buyer = new Buyer();
        Store store = new Store("Loja Teste","lojateste@hotmail.com","senhadaloja123","123456789023","Rua do Teste");
        buyer.getPurchaseHistory().add(new Product("Nintendo Switch 2", 4999,10, ProductType.VIDEO_GAME, "Nintendo", "Console Portátil",store));
        assertThrows(IllegalArgumentException.class,() -> marketplaceFacadeTest.rateStore(buyer,"Loja Teste", -5, "Boa Loja"));
    }

    @Test
    void ratingHigherStore(){
        Buyer  buyer = new Buyer();
        Store store = new Store("Loja Teste","lojateste@hotmail.com","senhadaloja123","123456789023","Rua do Teste");
        buyer.getPurchaseHistory().add(new Product("Nintendo Switch 2", 4999,10, ProductType.VIDEO_GAME, "Nintendo", "Console Portátil",store));
        assertThrows(IllegalArgumentException.class,() -> marketplaceFacadeTest.rateStore(buyer,"Loja Teste", 10, "Boa Loja"));
    }
}