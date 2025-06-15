package com.marketplace.model;

import com.marketplace.Enum.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product productTest;

    @BeforeEach
    void setUp() {
        Store store =  new Store();
        productTest = new Product("Xbox Series X", 4000, 20, ProductType.VIDEO_GAME, "Microsoft", "VideoGame Xbox Serie X", store);
    }

    @Test
    void getId() {
        assertEquals(0,productTest.getId());
    }

    @Test
    void setId() {
        productTest.setId(1);
        assertEquals(1, productTest.getId());
    }

    @Test
    void getName() {
        assertEquals("Xbox Series X", productTest.getName());
    }

    @Test
    void setName() {
        productTest.setName("Xbox Series S");
        assertEquals("Xbox Series S", productTest.getName());
    }

    @Test
    void getValue() {
        assertEquals(4000, productTest.getValue());
    }

    @Test
    void setValue() {
        productTest.setValue(4950);
        assertEquals(4950, productTest.getValue());
    }

    @Test
    void getQuantity() {
        assertEquals(20, productTest.getQuantity());
    }

    @Test
    void setQuantity() {
        productTest.setQuantity(80);
        assertEquals(80, productTest.getQuantity());
    }

    @Test
    void getType() {
        assertEquals(ProductType.VIDEO_GAME, productTest.getType());
    }

    @Test
    void setType() {
        productTest.setType(ProductType.ELETRONICO);
        assertEquals(ProductType.ELETRONICO, productTest.getType());
    }

    @Test
    void getBrand() {
        assertEquals("Microsoft", productTest.getBrand());
    }

    @Test
    void setBrand() {
        productTest.setBrand("Sony");
        assertEquals("Sony", productTest.getBrand());
    }

    @Test
    void getDescription() {
        assertEquals("VideoGame Xbox Serie X", productTest.getDescription());
    }

    @Test
    void setDescription() {
        productTest.setDescription("Videogame Xbox");
        assertEquals("Videogame Xbox", productTest.getDescription());
    }

    @Test
    void getStore() {
        assertNull(productTest.getStore().getName());
    }

    @Test
    void setStore() {
        productTest.setStore(new Store("Loja Teste","lojateste@hotmail.com","senhadaloja123","123456789023","Rua do Teste"));
        assertEquals("Loja Teste",  productTest.getStore().getName());
    }

    @Test
    void getRatings() {
        assertEquals(productTest.getRatings(),productTest.getRatings());
    }

    @Test
    void getComments() {
        assertEquals(productTest.getComments(),productTest.getComments());
    }

    @Test
    void addRating() {
        Buyer buyer = new Buyer();
        productTest.addRating(buyer, 3);
        assertEquals(3, productTest.getRatings().get(buyer));
    }

    @Test
    void addComment() {
        Buyer buyer = new Buyer();
        productTest.addComment(buyer, "Muito bom produto!");
        assertEquals("Muito bom produto!", productTest.getComments().get(buyer));
    }

    @Test
    void hasRatingFrom() {
        Buyer buyer = new Buyer();
        assertFalse(productTest.hasRatingFrom(buyer));
    }

    @Test
    void getAverageRating() {
        assertEquals(0,productTest.getAverageRating());
    }

    @Test
    void testToString() {
        String expected = "Product{" +
                "nome='" + productTest.getName() + '\'' +
                ", valor='" + productTest.getValue() + '\'' +
                ", quantidade='" + productTest.getQuantity() + '\'' +
                ", categoria='" + productTest.getType() + '\'' +
                ", marca='" + productTest.getBrand() + '\'' +
                ", descrição='" + productTest.getDescription() + '\'' +
                ", Avaliação='" + productTest.getAverageRating() + '\'' +
                '}';
        assertEquals(expected, productTest.toString());
    }
}