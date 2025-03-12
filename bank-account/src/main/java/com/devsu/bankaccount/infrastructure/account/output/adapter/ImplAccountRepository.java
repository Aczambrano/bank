package com.devsu.bankaccount.infrastructure.account.output.adapter;

import com.devsu.bankaccount.application.port.ouput.IAccountRepository;
import com.devsu.bankaccount.domain.model.Account;
import com.devsu.bankaccount.infrastructure.account.output.entity.AccountEntity;
import com.devsu.bankaccount.infrastructure.account.output.mapper.AccountMapper;
import com.devsu.bankaccount.infrastructure.account.output.repository.IOutputAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ImplAccountRepository implements IAccountRepository {

    private final IOutputAccountRepository accountRepository;

    public ImplAccountRepository(IOutputAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> findAll() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return accountEntities.stream()
                .map(AccountMapper::entityToAccount)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(Integer id) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        return accountEntity.map(AccountMapper::entityToAccount);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        Optional<AccountEntity> accountEntity = accountRepository.findByAccountNumber(accountNumber);
        return accountEntity.map(AccountMapper::entityToAccount);
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = AccountMapper.accountToEntity(account);
        AccountEntity savedEntity = accountRepository.save(accountEntity);
        return AccountMapper.entityToAccount(savedEntity);
    }

    @Override
    public Account update(Account account) {
        Optional<AccountEntity> existingEntity = accountRepository.findById(account.getAccountId());

        if (existingEntity.isPresent()) {
            AccountEntity updatedEntity = existingEntity.get();
            updatedEntity.setAccountNumber(account.getAccountNumber());
            updatedEntity.setAccountType(account.getAccountType());
            updatedEntity.setInitialBalance(account.getInitialBalance());
            updatedEntity.setStatus(account.getStatus());
            updatedEntity.setCustomerId(account.getCustomerId());
            accountRepository.save(updatedEntity);
            return AccountMapper.entityToAccount(updatedEntity);
        } else {
            throw new NoSuchElementException("Account not found");
        }
    }

    @Override
    public Optional<Account> findByCustomerId(Integer customerId) {
        Optional<AccountEntity> accountEntity = accountRepository.findByCustomerId(customerId);
        return accountEntity.map(AccountMapper::entityToAccount);
    }
}
