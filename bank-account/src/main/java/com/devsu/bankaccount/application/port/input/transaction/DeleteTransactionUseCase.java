package com.devsu.bankaccount.application.port.input.transaction;

import com.devsu.bankaccount.application.port.ouput.ITransactionrepository;

import java.util.NoSuchElementException;

public class DeleteTransactionUseCase {

    private final ITransactionrepository transactionrepository;

    public DeleteTransactionUseCase(ITransactionrepository transactionrepository) {
        this.transactionrepository = transactionrepository;
    }


    public void execute(Integer id) {
        transactionrepository.findById(id)
                .ifPresentOrElse(
                        transaction -> transactionrepository.delete(transaction),
                        () -> {
                            throw new NoSuchElementException("The transaction with the given id does not exist.");
                        }
                );
    }

}
