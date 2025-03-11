package com.devsu.bank_client.application.usecases.customer;

import com.devsu.bank_client.application.exception.ConflictException;
import com.devsu.bank_client.application.usecases.gateway.ICustomerRepository;
import com.devsu.bank_client.domain.Customer;

public class CreateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public CreateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer) {

        customerRepository.findByIdentification(customer.getIdentification())
                .ifPresent( customer1 -> {
                    throw new ConflictException("The customer with that identification is already registered.");
                });

        //customer.setPassword(PasswordUtils.encryptPassword(customer.getPassword()));
        return customerRepository.save(customer);
    }

}
