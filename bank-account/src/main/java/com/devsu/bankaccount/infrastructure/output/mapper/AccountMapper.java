package com.devsu.bankaccount.infrastructure.output.mapper;

import com.devsu.bankaccount.domain.model.Account;
import com.devsu.bankaccount.infrastructure.output.entity.AccountEntity;

public class AccountMapper {

    public static Account entityToAccount(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }

        Account account = new Account();
        account.setAccountId(accountEntity.getAccountId());
        account.setAccountNumber(accountEntity.getAccountNumber());
        account.setAccountType(accountEntity.getAccountType());
        account.setInitialBalance(accountEntity.getInitialBalance());
        account.setStatus(accountEntity.getStatus());
        account.setCustomerId(accountEntity.getCustomerId());
        return account;
    }

    public static AccountEntity accountToEntity(Account account) {
        if (account == null) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(account.getAccountId());
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setInitialBalance(account.getInitialBalance());
        accountEntity.setStatus(account.getStatus());
        accountEntity.setCustomerId(account.getCustomerId());
        return accountEntity;
    }

}

