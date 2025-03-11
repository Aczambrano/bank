package com.devsu.bank_client.infrastructure.adapters.input.mapper;

import com.devsu.bank_client.domain.Customer;
import com.devsu.bank_client.infrastructure.adapters.input.dto.CustomerRequestDTO;
import com.devsu.bank_client.infrastructure.adapters.input.dto.CustomerResponseDTO;

public class CustomerDTOMapper {

    public static Customer mapToEntity(CustomerRequestDTO customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setGender(customerRequest.getGender());
        customer.setAge(customerRequest.getAge());
        customer.setIdentification(customerRequest.getIdentification());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhone(customerRequest.getPhone());
        customer.setCustomerId(customerRequest.getCustomerId());
        customer.setPassword(customerRequest.getPassword());
        customer.setStatus(customerRequest.getStatus());
        return customer;
    }

    public static CustomerResponseDTO mapToResponse(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponseDTO(
                customer.getCustomerId(),
                customer.getName(),
                customer.getGender(),
                customer.getAge(),
                customer.getIdentification(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getStatus(),
                customer.getPassword()
        );
    }
}
