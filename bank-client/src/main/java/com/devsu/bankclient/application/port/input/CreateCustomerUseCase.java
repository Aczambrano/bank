package com.devsu.bankclient.application.port.input;

import com.devsu.bankclient.application.exception.ConflictException;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
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
