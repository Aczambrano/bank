package com.devsu.bankclient.application.port.input;

import com.devsu.bankclient.application.exception.EntityNotFoundException;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
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
