package com.marketplace.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    Store storeTest;

    @BeforeEach
    void setUp() {
        storeTest = new Store("Loja Teste","lojateste@hotmail.com","senhadaloja123","123456789023","Rua do Teste");
    }

    @Test
    void getId() {
        assertEquals(0, storeTest.getId());
    }

    @Test
    void setId() {
        storeTest.setId(1);
        assertEquals(1, storeTest.getId());
    }

    @Test
    void getName() {
        assertEquals("Loja Teste",storeTest.getName());
    }

    @Test
    void setName() {
        storeTest.setName("Loja de Jogos");
        assertEquals("Loja de Jogos",storeTest.getName());
    }

    @Test
    void getEmail() {
        assertEquals("lojateste@hotmail.com",storeTest.getEmail());
    }

    @Test
    void setEmail() {
        storeTest.setEmail("loja@outlook.com");
        assertEquals("loja@outlook.com",storeTest.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("senhadaloja123",storeTest.getPassword());
    }

    @Test
    void setPassword() {
        storeTest.setPassword("lojasenha321");
        assertEquals("lojasenha321",storeTest.getPassword());
    }

    @Test
    void getCnpj() {
        assertEquals("123456789023",storeTest.getCnpj());
    }

    @Test
    void setCnpj() {
        storeTest.setCnpj("938425023");
        assertEquals("938425023",storeTest.getCnpj());
    }

    @Test
    void getAddress() {
        assertEquals("Rua do Teste",storeTest.getAddress());
    }

    @Test
    void setAddress() {
        storeTest.setAddress("Complexo do Teste");
        assertEquals("Complexo do Teste",storeTest.getAddress());
    }

    @Test
    void getRatings() {
        assertEquals(storeTest.getRatings(),storeTest.getRatings());
    }

    @Test
    void getComments() {
        assertEquals(storeTest.getComments(),storeTest.getComments());
    }

    @Test
    void addRating() {
        Buyer buyer = new Buyer();
        storeTest.addRating(buyer, 3);
        assertEquals(3, storeTest.getRatings().get(buyer));
    }

    @Test
    void addComment() {
        Buyer buyer = new Buyer();
        storeTest.addComment(buyer, "Muito boa Loja!");
        assertEquals("Muito boa Loja!", storeTest.getComments().get(buyer));
    }

    @Test
    void hasRatingFrom() {
        Buyer buyer = new Buyer();
        assertFalse(storeTest.hasRatingFrom(buyer));
    }

    @Test
    void getAverageRating() {
        assertEquals(0,storeTest.getAverageRating());
    }

    @Test
    void testToString() {
        String expected = "Store{" +
                "nome='" + storeTest.getName() + '\'' +
                ", email='" + storeTest.getEmail() + '\'' +
                ", cnpj='" + storeTest.getCnpj() + '\'' +
                ", endereco='" + storeTest.getAddress() + '\'' +
                '}';

        assertEquals(expected, storeTest.toString());
    }
}