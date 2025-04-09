package com.marketplacees2.service;

import com.marketplace.Enum.ProductType;
import com.marketplace.model.Product;
import com.marketplace.repository.ProductRepository;
import com.marketplace.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Product createProduct(String name, double value, int quantity, ProductType productType, String brand, String description) {
        return new Product(name, value, quantity, productType, brand, description);
    }

    @Test
    void testAddProduct() {
        Product product = createProduct("Notebook Acer", 2500, 10, ProductType.ELETRONICO, "Acer", "Descrição");

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        doNothing().when(productRepository).addProduct(productCaptor.capture());

        productService.addProduct(product);

        verify(productRepository, times(1)).addProduct(productCaptor.capture());
        assertEquals(product.getName(), productCaptor.getValue().getName());
        assertEquals(product.getValue(), productCaptor.getValue().getValue());
    }

    @Test
    void testListProducts() {
        Product product1 = createProduct("Notebook Acer", 2500, 10, ProductType.ELETRONICO, "Acer", "Descrição");
        Product product2 = createProduct("Notebook Multilaser", 2000, 10, ProductType.ELETRONICO, "Multilaser", "Descrição");

        when(productRepository.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.listProducts();

        assertEquals(2, products.size());
        assertEquals("Notebook Acer", products.get(0).getName());
        assertEquals("Notebook Multilaser", products.get(1).getName());
    }

    @Test
    void testListProductsEmpty() {
        when(productRepository.getAllProducts()).thenReturn(Arrays.asList());

        List<Product> products = productService.listProducts();

        assertTrue(products.isEmpty(), "A lista de produtos deve estar vazia.");
    }

    @Test
    void testUpdateProduct_Success() {
        Product productToUpdate = createProduct("Monitor", 3000, 5, ProductType.ELETRONICO, "Acer", "Descrição");

        when(productRepository.updateProduct(productToUpdate)).thenReturn(true);

        boolean result = productService.updateProduct(productToUpdate);

        assertTrue(result);
        verify(productRepository, times(1)).updateProduct(productToUpdate);
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product productToUpdate = createProduct("Mouse", 300, 20, ProductType.ELETRONICO, "Logitech", "Descrição");

        when(productRepository.updateProduct(productToUpdate)).thenReturn(false);

        boolean result = productService.updateProduct(productToUpdate);

        assertFalse(result, "O produto não deveria ter sido atualizado, pois não foi encontrado.");
        verify(productRepository, times(1)).updateProduct(productToUpdate);
    }

    @Test
    void testRemoveProduct_Success() {
        int productId = 1;

        when(productRepository.removeProduct(productId)).thenReturn(true);

        boolean result = productService.removeProduct(productId);

        assertTrue(result);
        verify(productRepository, times(1)).removeProduct(productId);
    }

    @Test
    void testRemoveProduct_NotFound() {
        int productId = 99;

        when(productRepository.removeProduct(productId)).thenReturn(false);

        boolean result = productService.removeProduct(productId);

        assertFalse(result, "O produto não deveria ter sido removido, pois não foi encontrado.");
        verify(productRepository, times(1)).removeProduct(productId);
    }

    @Test
    void testAddProductThrowsException() {
        Product product = createProduct("Notebook Acer", 2500, 5, ProductType.ELETRONICO, "Acer", "Descrição");

        // Simula exceção no repositório
        doThrow(new RuntimeException("Erro ao adicionar produto")).when(productRepository).addProduct(product);

        assertThrows(RuntimeException.class, () -> {
            productService.addProduct(product);
        }, "Esperado que uma exceção seja lançada ao adicionar produto.");
    }

    @Test
    void testUpdateProductThrowsException() {
        Product productToUpdate = createProduct("Mouse", 300, 10, ProductType.ELETRONICO, "Logitech", "Descrição");

        // Simula exceção no repositório
        when(productRepository.updateProduct(productToUpdate)).thenThrow(new RuntimeException("Erro ao atualizar produto"));

        assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(productToUpdate);
        }, "Esperado que uma exceção seja lançada ao atualizar produto.");
    }
}
