package com.marketplace.model;

import com.marketplace.Enum.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    Buyer buyer;

    @BeforeEach
    void setUp() {
        buyer = new Buyer("Buyer1", "buyer@email.com", "senhacomprador1", "098765432190", "Rua Teste");
        buyer.setId(1);

        Store store = new Store();

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Xbox Series X", 4000, 20, ProductType.VIDEO_GAME, "Microsoft", "VideoGame Xbox Serie X", store));
        buyer.setCart(products);

        buyer.setPoints(800);
    }

    @Test
    void getId() {
        assertEquals(buyer.getId(), 1);
    }

    @Test
    void setId() {
        buyer.setId(2);
        assertEquals(buyer.getId(), 2);
    }

    @Test
    void getName() {
        assertEquals(buyer.getName(), "Buyer1");
    }

    @Test
    void setName() {
        buyer.setName("Buyer2");
        assertEquals(buyer.getName(), "Buyer2");
    }

    @Test
    void getEmail() {
        assertEquals(buyer.getEmail(),"buyer@email.com");
    }

    @Test
    void setEmail() {
        buyer.setEmail("novoemailbuyer@gmail.com");
        assertEquals("novoemailbuyer@gmail.com", buyer.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals(buyer.getPassword(), "senhacomprador1");
    }

    @Test
    void setPassword() {
        buyer.setPassword("senhacomprador2");
        assertEquals("senhacomprador2", buyer.getPassword());
    }

    @Test
    void getCpf() {
        assertEquals("098765432190", buyer.getCpf());
    }

    @Test
    void setCpf() {
        buyer.setCpf("12345678901");
        assertEquals("12345678901", buyer.getCpf());
    }

    @Test
    void getAddress() {
        assertEquals("Rua Teste",  buyer.getAddress());
    }

    @Test
    void setAddress() {
        buyer.setAddress("Avenida Teste");
        assertEquals("Avenida Teste", buyer.getAddress());
    }

    @Test
    void testToString() {
        String teste = "Admin{" +
                "nome='" + buyer.getName() + '\'' +
                ", email='" + buyer.getEmail() + '\'' +
                ", cpf='" + buyer.getCpf() + '\'' +
                ", endereco='" + buyer.getAddress() + '\'' +
                '}';
        assertEquals(buyer.toString(), buyer.toString());
    }

    @Test
    void getCart() {
        List<Product> carrinho = buyer.getCart();

        assertEquals(carrinho.size(), 1);
        assertEquals(carrinho.get(0).getName(), "Xbox Series X");
    }

    @Test
    void setCart() {
        List<Product> carrinho = buyer.getCart();

        Store store = new Store();
        carrinho.add(new Product("Uma breve história do tempo", 40, 400, ProductType.LIVRO, "Tilibra", "Escrito por Stephen Hawking", store));

        buyer.setCart(carrinho);

        assertEquals(buyer.getCart().size(), 2);
    }

    @Test
    void getPurchaseHistory() {
        assertEquals(buyer.getPurchaseHistory().size(), 0);
    }

    @Test
    void getPoints() {
        assertEquals(buyer.getPoints(), 800);
    }

    @Test
    void setPoints() {
        buyer.setPoints(120);
        assertEquals(120, buyer.getPoints());
    }

    @Test
    void addPoints() {
        buyer.addPoints(buyer.getCart().get(0));
        assertEquals(1600, buyer.getPoints());
    }

    @Test
    void addToCart() {
        Store store = new Store();
        buyer.addToCart(new Product("Uma breve história do tempo", 40, 400, ProductType.LIVRO, "Tilibra", "Escrito por Stephen Hawking", store));

        List<Product> carrinho = buyer.getCart();

        assertEquals("Uma breve história do tempo", carrinho.get(1).getName());
    }

    @Test
    void deleteFromCart() {
        buyer.deleteFromCart(buyer.getCart().get(0));

        assertEquals(0, buyer.getCart().size());
    }

    @Test
    void cleanCart() {
        buyer.cleanCart();

        assertEquals(0, buyer.getCart().size());
    }

    @Test
    void listPurchases() {
        buyer.listPurchases();
    }
}