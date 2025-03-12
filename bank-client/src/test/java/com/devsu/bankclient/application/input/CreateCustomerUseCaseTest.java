package com.devsu.bankclient.application.input;

import com.devsu.bankclient.application.exception.ConflictException;
import com.devsu.bankclient.application.port.input.CreateCustomerUseCase;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.application.port.output.IPasswordEncoder;
import com.devsu.bankclient.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CreateCustomerUseCaseTest {
    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private IPasswordEncoder passwordEncoder;

    private CreateCustomerUseCase createCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createCustomerUseCase = new CreateCustomerUseCase(customerRepository, passwordEncoder);
    }

    @Test
    void testExecuteSuccessfully() {
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "123 Main St", "555-1234", 1, "password123", true);

        when(customerRepository.findByIdentification(customer.getIdentification())).thenReturn(Optional.empty());

        when(passwordEncoder.encode(customer.getPassword())).thenReturn("encodedPassword");

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = createCustomerUseCase.execute(customer);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(customerRepository).save(customer);
        verify(customerRepository).findByIdentification(customer.getIdentification());
    }

    @Test
    void testExecuteWithConflict() {
        Customer customer = new Customer("John Doe", "Male", 30, "123456789", "123 Main St", "555-1234", 1, "password123", true);

        when(customerRepository.findByIdentification(customer.getIdentification())).thenReturn(Optional.of(customer));

        ConflictException exception = assertThrows(ConflictException.class, () -> createCustomerUseCase.execute(customer));

        assertEquals("The customer with that identification is already registered.", exception.getMessage());
        verify(customerRepository, never()).save(customer);
    }
}
