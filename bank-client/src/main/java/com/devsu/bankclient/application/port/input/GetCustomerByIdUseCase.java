package com.devsu.bankclient.application.port.input;

import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCustomerByIdUseCase {

    private final ICustomerRepository customerRepository;
    public GetCustomerByIdUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> execute(Integer id) {
        return customerRepository.findById(id);
    }
}
