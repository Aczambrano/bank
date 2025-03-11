package com.devsu.bank_client.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe", "Male", 30, "ID12345",
                "1234 Elm Street", "555-1234", 1, "password123", true);
    }

    @Test
    void testCustomerConstructor() {
        assertEquals("John Doe", customer.getName());
        assertEquals("Male", customer.getGender());
        assertEquals(30, customer.getAge());
        assertEquals("ID12345", customer.getIdentification());
        assertEquals("1234 Elm Street", customer.getAddress());
        assertEquals("555-1234", customer.getPhone());
        assertEquals(1, customer.getCustomerId());
        assertEquals("password123", customer.getPassword());
        assertTrue(customer.getStatus());
    }

    @Test
    void testCustomerSettersAndGetters() {
        customer.setCustomerId(2);
        customer.setPassword("newpassword");
        customer.setStatus(false);

        assertEquals(2, customer.getCustomerId());
        assertEquals("newpassword", customer.getPassword());
        assertFalse(customer.getStatus());
    }

    @Test
    void testCustomerDefaultConstructor() {
        Customer newCustomer = new Customer();
        assertNull(newCustomer.getCustomerId());
        assertNull(newCustomer.getPassword());
        assertNull(newCustomer.getStatus());
    }
}
