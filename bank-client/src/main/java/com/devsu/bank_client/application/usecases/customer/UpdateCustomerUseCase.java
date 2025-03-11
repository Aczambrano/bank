package com.devsu.bank_client.application.usecases.customer;

import com.devsu.bank_client.application.exception.EntityNotFoundException;
import com.devsu.bank_client.application.usecases.gateway.ICustomerRepository;
import com.devsu.bank_client.domain.Customer;

public class UpdateCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public UpdateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer){

        customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        //customer.setPassword(PasswordUtils.encryptPassword(customer.getPassword()));


        return customerRepository.update(customer);
    }
}
