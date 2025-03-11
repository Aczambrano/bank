package com.devsu.bankclient.application.port.input;

import com.devsu.bankclient.application.exception.ConflictException;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.application.port.output.IPasswordEncoder;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerUseCase {

    private final ICustomerRepository customerRepository;
    private final IPasswordEncoder passwordEncoder;

    public CreateCustomerUseCase(ICustomerRepository customerRepository, IPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer execute(Customer customer) {

        customerRepository.findByIdentification(customer.getIdentification())
                .ifPresent( customer1 -> {
                    throw new ConflictException("The customer with that identification is already registered.");
                });

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

}
