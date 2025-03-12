package com.devsu.bankaccount.infrastructure.output.repository;

import com.devsu.bankaccount.infrastructure.output.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOutputAccountRepository extends JpaRepository<AccountEntity, Integer> {
    Optional<AccountEntity> findById(Integer id);
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    List<AccountEntity> findAll();
    List<AccountEntity> findByCustomerId(Integer id);
}
