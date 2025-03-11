package com.devsu.bankaccount.application.port.input.transaction;

import com.devsu.bankaccount.application.port.ouput.ITransactionrepository;
import com.devsu.bankaccount.domain.model.Transaction;

import java.util.List;

public class FindAllTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public FindAllTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }

    public List<Transaction> execute() {
        return transactionrepository.findAll();
    }

}
