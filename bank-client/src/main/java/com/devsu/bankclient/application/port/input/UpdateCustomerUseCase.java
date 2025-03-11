package com.devsu.bankclient.application.port.input;

import com.devsu.bankclient.application.exception.EntityNotFoundException;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
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
