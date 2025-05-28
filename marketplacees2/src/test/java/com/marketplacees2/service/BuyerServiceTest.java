package com.marketplacees2.service;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Buyer;
import com.marketplace.model.Product;
import com.marketplace.model.Store;
import com.marketplace.repository.BuyerRepository;
import com.marketplace.service.BuyerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuyerServiceTest {

    private BuyerRepository buyerRepository;
    private BuyerService buyerService;
    private Store testStore;

    @BeforeEach
    void setUp() {
        buyerRepository = mock(BuyerRepository.class);
        buyerService = new BuyerService(buyerRepository);
        testStore =  new Store("Test Store", "test@store.com", "12345", "1938598489", "rua teste");

    }

    private Buyer createBuyer(String name, String email) {
        return new Buyer(name, email, "12345678", "12345689-00", "Rua exemplo");
    }

    @Test
    void testAddBuyer() {
        Buyer buyer = createBuyer("John Doe", "john@example.com");

        ArgumentCaptor<Buyer> buyerCaptor = ArgumentCaptor.forClass(Buyer.class);
        doNothing().when(buyerRepository).addBuyer(buyerCaptor.capture());

        buyerService.addBuyer(buyer);

        verify(buyerRepository, times(1)).addBuyer(buyerCaptor.capture());

        assertEquals(buyer.getName(), buyerCaptor.getValue().getName());
        assertEquals(buyer.getEmail(), buyerCaptor.getValue().getEmail());
    }

    @Test
    void testListBuyers() {
        List<Buyer> buyers = Arrays.asList(
                createBuyer("John Doe", "john@example.com"),
                createBuyer("Jane Doe", "jane@example.com")
        );

        when(buyerRepository.getAllBuyers()).thenReturn(buyers);

        List<Buyer> result = buyerService.listBuyers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    void testListBuyersEmpty() {
        when(buyerRepository.getAllBuyers()).thenReturn(Arrays.asList());

        List<Buyer> result = buyerService.listBuyers();

        assertTrue(result.isEmpty(), "A lista de buyers deve estar vazia.");
    }

    @Test
    void testUpdateBuyer() {
        Buyer buyer = createBuyer("John Updated", "johnUpdated@example.com");

        when(buyerRepository.updateBuyer(buyer)).thenReturn(true);

        boolean result = buyerService.updateBuyer(buyer);

        assertTrue(result);
        verify(buyerRepository, times(1)).updateBuyer(buyer);
    }

    @Test
    void testUpdateBuyerNotFound() {
        Buyer buyer = createBuyer("John Updated", "johnUpdated@example.com");

        // Quando o repositório não encontrar o buyer, ele deve retornar false
        when(buyerRepository.updateBuyer(buyer)).thenReturn(false);

        boolean result = buyerService.updateBuyer(buyer);

        assertFalse(result, "O buyer não deveria ter sido atualizado, pois não foi encontrado.");
        verify(buyerRepository, times(1)).updateBuyer(buyer);
    }

    @Test
    void testRemoveBuyer() {
        int id = 1;

        when(buyerRepository.removeBuyer(id)).thenReturn(true);

        boolean result = buyerService.removeBuyer(id);

        assertTrue(result);
        verify(buyerRepository, times(1)).removeBuyer(id);
    }

    @Test
    void testRemoveBuyerNotFound() {
        int id = 1;

        when(buyerRepository.removeBuyer(id)).thenReturn(false);

        boolean result = buyerService.removeBuyer(id);

        assertFalse(result, "O buyer não deveria ter sido removido, pois não foi encontrado.");
        verify(buyerRepository, times(1)).removeBuyer(id);
    }

    @Test
    void testAddBuyerThrowsException() {
        Buyer buyer = createBuyer("John Doe", "john@example.com");

        // Simula exceção no repositório
        doThrow(new RuntimeException("Erro ao adicionar buyer")).when(buyerRepository).addBuyer(buyer);

        assertThrows(RuntimeException.class, () -> {
            buyerService.addBuyer(buyer);
        }, "Esperado que uma exceção seja lançada ao adicionar buyer.");
    }


    @Test
    void testBuyProduct_RemovesProductFromCart() {

        Buyer buyer = new Buyer();
        Product product1 = new Product("Notebook Acer", 2500, 2, ProductType.ELETRONICO, "Acer", "Descrição", testStore);
        Product product2 = new Product("Notebook Multilaser", 2000, 2, ProductType.ELETRONICO, "Multilaser", "Descrição", testStore);

        buyer.addToCart(product1);
        buyer.addToCart(product2);

        assertTrue(buyer.getCart().contains(product1));

        boolean result = buyer.buyProduct(product1, 0);

        assertTrue(result);
        assertFalse(buyer.getCart().contains(product1));
        assertEquals(1, product1.getQuantity());
    }

    @Test
    void testFinalizePurchase_ClearsCart() {
        Buyer buyer = new Buyer();
        Product product1 = new Product("Notebook Acer", 2500, 1, ProductType.ELETRONICO, "Acer", "Descrição", testStore);
        Product product2 = new Product("Notebook Multilaser", 2000, 1, ProductType.ELETRONICO, "Multilaser", "Descrição", testStore);

        buyer.addToCart(product1);
        buyer.addToCart(product2);

        boolean result = buyer.finalizePurchase(0);

        assertTrue(result);
        assertTrue(buyer.getCart().isEmpty());
        assertEquals(0, product1.getQuantity());
        assertEquals(0, product2.getQuantity());
    }

    @Test
    void testAddToCart_ProductIsAdded(){

        Buyer buyer = new Buyer();
        Product product1 = new Product("Notebook Acer", 2500, 1, ProductType.ELETRONICO, "Acer", "Descrição", testStore);

        buyer.addToCart(product1);

        List<Product> cart = buyer.getCart();

        assertNotNull(cart);
        assertEquals(1,cart.size());
        assertTrue(cart.contains(product1));

    }


    @Test
    void listPurchases () {
        Buyer buyer = new Buyer();
        Product product1 = new Product("Notebook Acer", 2500, 1, ProductType.ELETRONICO, "Acer", "Descrição", testStore);
        Product product2 = new Product("Notebook Multilaser", 2000, 1, ProductType.ELETRONICO, "Multilaser", "Descrição", testStore);

        buyer.addToCart(product1);
        buyer.addToCart(product2);

        List<Product> purchases = buyer.getCart();

        assertNotNull(purchases);
        assertEquals(2, purchases.size());
        assertTrue(purchases.contains(product1));
        assertTrue(purchases.contains(product2));
    }


}

