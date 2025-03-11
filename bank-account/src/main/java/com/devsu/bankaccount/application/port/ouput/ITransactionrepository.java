package com.devsu.bankaccount.application.port.ouput;

import com.devsu.bankaccount.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionrepository {
    List<Transaction> findAll();
    Optional<Transaction> findById(Integer id);
    Optional<Transaction>  findByAccountId(Integer accountId);
    Transaction save(Transaction transaction);
    Transaction update(Transaction transaction);
    Transaction delete(Transaction transaction);
}
