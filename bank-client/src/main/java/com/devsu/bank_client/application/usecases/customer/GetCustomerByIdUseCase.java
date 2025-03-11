package com.devsu.bank_client.application.usecases.customer;

import com.devsu.bank_client.application.usecases.gateway.ICustomerRepository;
import com.devsu.bank_client.domain.Customer;

import java.util.Optional;

public class GetCustomerByIdUseCase {

    private final ICustomerRepository customerRepository;
    public GetCustomerByIdUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> execute(Integer id) {
        return customerRepository.findById(id);
    }
}
