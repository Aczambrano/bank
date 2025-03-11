package com.devsu.bankaccount.application.port.input.account;

import com.devsu.bankaccount.application.exception.ConflictException;
import com.devsu.bankaccount.application.port.ouput.IAccountRepository;
import com.devsu.bankaccount.application.port.ouput.IRabbitMessage;
import com.devsu.bankaccount.domain.model.Account;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CreateAccountUseCase {

    private final IAccountRepository accountRepository;
    private final IRabbitMessage rabbitMessage;

    public CreateAccountUseCase(IAccountRepository accountRepository, IRabbitMessage rabbitMessage) {
        this.accountRepository = accountRepository;
        this.rabbitMessage = rabbitMessage;
    }

    public Account execute(Account account) {

        accountRepository.findByAccountNumber(account.getAccountNumber())
                .ifPresent(
                        account1 -> {
                            throw new ConflictException("Account already exists");
                        }
                );
        Optional.ofNullable(rabbitMessage.sendMessage(account.getCustomerId()))
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElseThrow(() -> new NoSuchElementException("Customer id not found"));

        return accountRepository.save(account);
    }

}
