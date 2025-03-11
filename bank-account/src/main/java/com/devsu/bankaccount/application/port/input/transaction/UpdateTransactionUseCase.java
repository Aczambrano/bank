package com.devsu.bankaccount.application.port.input.transaction;

import com.devsu.bankaccount.application.port.ouput.ITransactionrepository;
import com.devsu.bankaccount.domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public UpdateTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }

    public Transaction execute(Transaction transaction) {
        return transactionrepository.findByAccountId(transaction.getAccountId())
                .map(existingTransaction -> {
                    existingTransaction.setTransactionType(transaction.getTransactionType());
                    existingTransaction.setValue(transaction.getValue());
                    existingTransaction.setBalance(transaction.getBalance());
                    existingTransaction.setDate(LocalDateTime.now());
                    return transactionrepository.update(existingTransaction);
                })
                .orElseThrow(() -> new NoSuchElementException("The transaction with the given account id does not exist."));
    }

}
