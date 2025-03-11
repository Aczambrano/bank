package com.devsu.bankclient.infrastructure.adapters.output.adapter;

import com.devsu.bankclient.application.exception.ConflictException;
import com.devsu.bankclient.application.port.output.ICustomerRepository;
import com.devsu.bankclient.domain.model.Customer;
import com.devsu.bankclient.infrastructure.adapters.output.entity.CustomerEntity;
import com.devsu.bankclient.infrastructure.adapters.output.mapper.CustomerMapper;
import com.devsu.bankclient.infrastructure.adapters.output.repository.IOutPutCustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ImplCustomerRepository implements ICustomerRepository {

    private final IOutPutCustomerRepository customerRepository;

    public ImplCustomerRepository(IOutPutCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerMapper.dtoToEntity(customer);

        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return CustomerMapper.entityToDto(savedEntity);
    }

    @Override
    public Customer update(Customer customer) {
        CustomerEntity customerEntity = CustomerMapper.dtoToEntity(customer);

        Optional<CustomerEntity> existingEntity = customerRepository.findById(customer.getCustomerId());

        if (existingEntity.isPresent()) {
            CustomerEntity updatedEntity = existingEntity.get();
            updatedEntity.setName(customer.getName());
            updatedEntity.setGender(customer.getGender());
            updatedEntity.setAge(customer.getAge());
            updatedEntity.setIdentification(customer.getIdentification());
            updatedEntity.setAddress(customer.getAddress());
            updatedEntity.setPhone(customer.getPhone());
            updatedEntity.setCustomerId(customer.getCustomerId());
            updatedEntity.setPassword(customer.getPassword());
            updatedEntity.setStatus(customer.getStatus());

            customerRepository.save(updatedEntity);
            return CustomerMapper.entityToDto(updatedEntity);
        } else {
            throw new ConflictException("The customer with the given identification does not exist.");
        }
    }

    @Override
    public List<Customer> findAll() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream()
                .map(CustomerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        return customerEntity.map(CustomerMapper::entityToDto);
    }

    @Override
    public Optional<Customer> findByIdentification(String identification) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByIdentification(identification);
        return customerEntity.map(CustomerMapper::entityToDto);
    }

}
