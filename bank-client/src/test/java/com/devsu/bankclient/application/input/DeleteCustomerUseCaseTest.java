package com.devsu.bankclient.application.input;

import com.devsu.bankclient.application.exception.EntityNotFoundException;
import com.devsu.bankclient.application.port.input.DeleteCustomerUseCase;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeleteCustomerUseCaseTest {

    @Mock
    private ICustomerRepository customerRepository;

    private DeleteCustomerUseCase deleteCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteCustomerUseCase = new DeleteCustomerUseCase(customerRepository);
    }

    @Test
    void testExecuteSuccessfully() {
        Integer customerId = 1;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setStatus(true);

        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(customer));

        when(customerRepository.update(customer)).thenReturn(customer);

        deleteCustomerUseCase.execute(customerId);

        assertFalse(customer.getStatus());
        verify(customerRepository).update(customer);
    }

    @Test
    void testExecuteWithCustomerNotFound() {
        Integer customerId = 1;

        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> deleteCustomerUseCase.execute(customerId));

        assertEquals("Customer not found", exception.getMessage());
        verify(customerRepository, never()).update(any());
    }
}
