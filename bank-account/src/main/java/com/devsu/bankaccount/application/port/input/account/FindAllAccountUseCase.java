package com.devsu.bankaccount.application.port.input.account;

import com.devsu.bankaccount.application.port.ouput.IAccountRepository;
import com.devsu.bankaccount.domain.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllAccountUseCase {

    private final IAccountRepository accountRepository;

    public FindAllAccountUseCase(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> execute() {
        return accountRepository.findAll();
    }

}
