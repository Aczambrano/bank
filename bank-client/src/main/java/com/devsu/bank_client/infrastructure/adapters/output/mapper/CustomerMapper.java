package com.devsu.bank_client.infrastructure.adapters.output.mapper;

import com.devsu.bank_client.domain.Customer;
import com.devsu.bank_client.infrastructure.adapters.output.entity.CustomerEntity;

public class CustomerMapper {

    public static Customer entityToDto(CustomerEntity entity) {
        return new Customer(
                entity.getName(),
                entity.getGender(),
                entity.getAge(),
                entity.getIdentification(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getCustomerId(),
                entity.getPassword(),
                entity.getStatus()
        );
    }

    public static CustomerEntity dtoToEntity(Customer customer) {
        return new CustomerEntity(
                customer.getName(),
                customer.getGender(),
                customer.getAge(),
                customer.getIdentification(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getPassword(),
                customer.getStatus()
        );
    }
}
