package com.devsu.bankclient.domain;

import com.devsu.bankclient.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe", "Male", 30, "123456789", "123 Main St", "555-1234", 1, "password123", true);
    }

    @Test
    void testConstructor() {
        assertNotNull(customer);
        assertEquals("John Doe", customer.getName());
        assertEquals("Male", customer.getGender());
        assertEquals(30, customer.getAge());
        assertEquals("123456789", customer.getIdentification());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals("555-1234", customer.getPhone());
        assertEquals(1, customer.getCustomerId());
        assertEquals("password123", customer.getPassword());
        assertTrue(customer.getStatus());
    }

    @Test
    void testGettersAndSetters() {
        customer.setCustomerId(2);
        customer.setPassword("newpassword");
        customer.setStatus(false);

        assertEquals(2, customer.getCustomerId());
        assertEquals("newpassword", customer.getPassword());
        assertFalse(customer.getStatus());
    }

    @Test
    void testDefaultConstructor() {
        Customer defaultCustomer = new Customer();

        assertNull(defaultCustomer.getCustomerId());
        assertNull(defaultCustomer.getPassword());
        assertNull(defaultCustomer.getStatus());
    }

    @Test
    void testEquality() {
        Customer anotherCustomer = new Customer("Jane Doe", "Female", 25, "987654321", "456 Elm St", "555-4321", 1, "password123", true);

        assertEquals(customer.getCustomerId(), anotherCustomer.getCustomerId());
    }

    @Test
    void testInequality() {
        Customer anotherCustomer = new Customer("Jane Doe", "Female", 25, "987654321", "456 Elm St", "555-4321", 2, "password123", true);

        assertNotEquals(customer.getCustomerId(), anotherCustomer.getCustomerId());
    }


}
