package com.devsu.bank_client.infrastructure.adapters.output.repository;

import com.devsu.bank_client.infrastructure.adapters.output.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findById(Integer id);
    Optional<CustomerEntity> findByIdentification(String identification);
    List<CustomerEntity> findAll();
}
