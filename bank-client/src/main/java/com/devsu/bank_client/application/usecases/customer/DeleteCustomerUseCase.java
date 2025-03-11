package com.devsu.bank_client.application.usecases.customer;

import com.devsu.bank_client.application.exception.EntityNotFoundException;
import com.devsu.bank_client.application.usecases.gateway.ICustomerRepository;
import com.devsu.bank_client.domain.Customer;

public class DeleteCustomerUseCase {


    private final ICustomerRepository customerRepository;

    public DeleteCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(Integer identification) {

        Customer customer = customerRepository.findById(identification)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setStatus(false);
        customerRepository.update(customer);
    }

}
