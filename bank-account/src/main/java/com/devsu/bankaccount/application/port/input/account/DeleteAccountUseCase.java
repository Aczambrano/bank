package com.devsu.bankaccount.application.port.input.account;

import com.devsu.bankaccount.application.port.ouput.IAccountRepository;

import java.util.NoSuchElementException;

public class DeleteAccountUseCase {

    private final IAccountRepository accountRepository;

    public DeleteAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void execute(Integer accountId) {

        accountRepository.findById(accountId)
                .map(account -> {
                    account.setStatus(false);
                    return accountRepository.save(account);
                })
                .orElseThrow(() -> new NoSuchElementException("The account with the given id does not exist."));
    }

}
