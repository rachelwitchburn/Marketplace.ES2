package com.marketplace.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin("Admin1", "admin@email.com", "senhaadmin1", "098765432190", "Rua Teste");
        admin.setId(1);
    }

    @Test
    void getId() {
        assertEquals(admin.getId(), 1);
    }

    @Test
    void setId() {
        admin.setId(2);
        assertEquals(admin.getId(), 2);
    }

    @Test
    void getName() {
        assertEquals(admin.getName(), "Admin1");
    }

    @Test
    void setName() {
        admin.setName("Admin2");
        assertEquals(admin.getName(), "Admin2");
    }

    @Test
    void getEmail() {
        assertEquals(admin.getEmail(),"admin@email.com");
    }

    @Test
    void setEmail() {
        admin.setEmail("novoemailadmin@gmail.com");
        assertEquals("novoemailadmin@gmail.com", admin.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals(admin.getPassword(), "senhaadmin1");
    }

    @Test
    void setPassword() {
        admin.setPassword("senhaadmin2");
        assertEquals("senhaadmin2", admin.getPassword());
    }

    @Test
    void getCpf() {
        assertEquals("098765432190",admin.getCpf());
    }

    @Test
    void setCpf() {
        admin.setCpf("12345678901");
        assertEquals("12345678901",admin.getCpf());
    }

    @Test
    void getAddress() {
        assertEquals("Rua Teste",  admin.getAddress());
    }

    @Test
    void setAddress() {
        admin.setAddress("Avenida Teste");
        assertEquals("Avenida Teste", admin.getAddress());
    }

    @Test
    void testToString() {
        String teste = "Admin{" +
                "nome='" + admin.getName() + '\'' +
                ", email='" + admin.getEmail() + '\'' +
                ", cpf='" + admin.getCpf() + '\'' +
                ", endereco='" + admin.getAddress() + '\'' +
                '}';
        assertEquals(admin.toString(), admin.toString());
    }
}