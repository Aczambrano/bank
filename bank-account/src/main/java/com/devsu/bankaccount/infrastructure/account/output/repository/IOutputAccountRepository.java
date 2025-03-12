package com.devsu.bankaccount.infrastructure.account.output.repository;

import com.devsu.bankaccount.infrastructure.account.output.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOutputAccountRepository extends JpaRepository<AccountEntity, Integer> {
    Optional<AccountEntity> findById(Integer id);
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    List<AccountEntity> findAll();
    Optional<AccountEntity> findByCustomerId(Integer id);
}
