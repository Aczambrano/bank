package com.devsu.bankaccount.application.port.ouput;

import com.devsu.bankaccount.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
    List<Account> findAll();
    Optional<Account> findById(Integer id);
    Optional<Account>  findByAccountNumber(String accountNumber);
    Account save(Account account);
    Account update(Account account);
    Optional<Account> findByCustomerId(Integer customerId);
}
