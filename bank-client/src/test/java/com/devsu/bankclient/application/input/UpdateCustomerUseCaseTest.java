package com.devsu.bankclient.application.input;

import com.devsu.bankclient.application.exception.EntityNotFoundException;
import com.devsu.bankclient.application.port.input.UpdateCustomerUseCase;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.application.port.output.IPasswordEncoder;
import com.devsu.bankclient.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

public class UpdateCustomerUseCaseTest {
    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private IPasswordEncoder passwordEncoder;

    private UpdateCustomerUseCase updateCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateCustomerUseCase = new UpdateCustomerUseCase(customerRepository, passwordEncoder);
    }

    @Test
    void testExecuteSuccessfully() {
        Integer customerId = 1;
        Customer customer = new Customer(customerId, "password123", true);

        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(customer));

        when(passwordEncoder.encode(customer.getPassword())).thenReturn("encodedPassword");

        when(customerRepository.update(customer)).thenReturn(customer);

        Customer result = updateCustomerUseCase.execute(customer);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(customerRepository).update(customer);
    }

    @Test
    void testExecuteWithCustomerNotFound() {
        Integer customerId = 1;
        Customer customer = new Customer(customerId, "password123", true);

        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.empty());

        // Llamamos al método `execute` y esperamos una excepción
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> updateCustomerUseCase.execute(customer));

        assertEquals("Customer not found", exception.getMessage());
        verify(customerRepository, never()).update(any());
    }
}
