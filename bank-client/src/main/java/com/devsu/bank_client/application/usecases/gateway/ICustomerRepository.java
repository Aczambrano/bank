package com.devsu.bank_client.application.usecases.gateway;

import com.devsu.bank_client.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {
    Customer save(Customer customer);
    Customer update(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById(Integer id);
    Optional<Customer> findByIdentification(String identification);
}
