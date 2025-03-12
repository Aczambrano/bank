package com.devsu.bankclient.infrastructure.output.repository;

import com.devsu.bankclient.infrastructure.output.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class IOutPutCustomerRepositoryTest {

    @Autowired
    private IOutPutCustomerRepository customerRepository;

    private CustomerEntity customerEntity;

    @BeforeEach
    void setUp() {
        customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(1);
        customerEntity.setName("John Doe");
        customerEntity.setIdentification("12345");
        customerEntity.setPassword("password123");
        customerEntity.setStatus(true);

        customerRepository.save(customerEntity);
    }

    @Test
    void testFindById() {
        Optional<CustomerEntity> foundCustomer = customerRepository.findById(1);
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Doe", foundCustomer.get().getName());
    }

    @Test
    void testFindByIdentification() {
        Optional<CustomerEntity> foundCustomer = customerRepository.findByIdentification("12345");
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Doe", foundCustomer.get().getName());
    }

    @Test
    void testFindAll() {
        assertFalse(customerRepository.findAll().isEmpty());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<CustomerEntity> foundCustomer = customerRepository.findById(999);
        assertFalse(foundCustomer.isPresent());
    }

    @Test
    void testFindByIdentificationNotFound() {
        Optional<CustomerEntity> foundCustomer = customerRepository.findByIdentification("99999");
        assertFalse(foundCustomer.isPresent());
    }

}
