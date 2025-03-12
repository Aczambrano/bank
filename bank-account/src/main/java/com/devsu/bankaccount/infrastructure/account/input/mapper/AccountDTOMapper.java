package com.devsu.bankaccount.infrastructure.account.input.mapper;

import com.devsu.bankaccount.domain.model.Account;
import com.devsu.bankaccount.infrastructure.account.input.dto.AccountRequestDTO;
import com.devsu.bankaccount.infrastructure.account.input.dto.AccountResponseDTO;

public class AccountDTOMapper {


    public static AccountResponseDTO toAccountResponseDTO(Account account) {
        return new AccountResponseDTO(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getInitialBalance(),
                account.getStatus(),
                account.getCustomerId()
        );
    }

    public static Account toAccount(AccountRequestDTO accountRequestDTO) {
        return new Account(
                accountRequestDTO.getAccountId(),
                accountRequestDTO.getAccountNumber(),
                accountRequestDTO.getAccountType(),
                accountRequestDTO.getInitialBalance(),
                accountRequestDTO.getStatus(),
                accountRequestDTO.getCustomerId()
        );
    }

}
