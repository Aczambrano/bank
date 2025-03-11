package com.devsu.bank_client.application.usecases.customer;

import com.devsu.bank_client.application.usecases.gateway.ICustomerRepository;
import com.devsu.bank_client.domain.Customer;

import java.util.List;

public class FindAllCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public FindAllCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> execute() {
        return customerRepository.findAll();
    }
}
