package com.devsu.bankclient.application.port.input;

import com.devsu.bankclient.application.exception.EntityNotFoundException;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.application.port.output.IPasswordEncoder;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerUseCase {

    private final ICustomerRepository customerRepository;
    private final IPasswordEncoder passwordEncoder;

    public UpdateCustomerUseCase(ICustomerRepository customerRepository, IPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer execute(Customer customer){

        customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.update(customer);
    }
}
