package com.devsu.bankclient.application.port.input;

import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCustomerUseCase {

    private final ICustomerRepository customerRepository;

    public FindAllCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> execute() {
        return customerRepository.findAll();
    }
}
