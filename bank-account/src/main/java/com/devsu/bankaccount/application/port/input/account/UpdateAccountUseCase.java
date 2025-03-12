package com.devsu.bankaccount.application.port.input.account;

import com.devsu.bankaccount.application.port.ouput.IAccountRepository;
import com.devsu.bankaccount.application.port.ouput.IRabbitMessage;
import com.devsu.bankaccount.domain.model.Account;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UpdateAccountUseCase {
    private final IAccountRepository accountRepository;
    private final IRabbitMessage rabbitMessage;
    public UpdateAccountUseCase(IAccountRepository accountRepository, IRabbitMessage rabbitMessage) {
        this.accountRepository = accountRepository;
        this.rabbitMessage = rabbitMessage;
    }

    public Account execute(Account account) {

        Optional.ofNullable(rabbitMessage.sendMessage(account.getCustomerId()))
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElseThrow(() -> new NoSuchElementException("Customer id not found"));

        return accountRepository.findById(account.getAccountId())
                .map(existingAccount -> {
                    existingAccount.setAccountType(account.getAccountType());
                    existingAccount.setInitialBalance(account.getInitialBalance());
                    existingAccount.setStatus(account.getStatus());
                    return accountRepository.update(existingAccount);
                })
                .orElseThrow(() -> new NoSuchElementException("The account with the given id does not exist."));
    }
}
