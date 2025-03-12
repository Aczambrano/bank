package com.devsu.bankaccount.infrastructure.input.handler;

import com.devsu.bankaccount.application.port.input.account.CreateAccountUseCase;
import com.devsu.bankaccount.application.port.input.account.DeleteAccountUseCase;
import com.devsu.bankaccount.application.port.input.account.FindAllAccountUseCase;
import com.devsu.bankaccount.application.port.input.account.UpdateAccountUseCase;
import com.devsu.bankaccount.domain.model.Account;
import com.devsu.bankaccount.infrastructure.input.dto.AccountRequestDTO;
import com.devsu.bankaccount.infrastructure.input.dto.AccountResponseDTO;
import com.devsu.bankaccount.infrastructure.input.mapper.AccountDTOMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountHandler {

    private final com.devsu.bankaccount.application.port.input.account.CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final FindAllAccountUseCase findAllAccountUseCase;

    public AccountHandler(CreateAccountUseCase createAccountUseCase, UpdateAccountUseCase updateAccountUseCase,
                          DeleteAccountUseCase deleteAccountUseCase, FindAllAccountUseCase findAllAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
        this.findAllAccountUseCase = findAllAccountUseCase;
    }


    public List<AccountResponseDTO> getAccounts() {
        return findAllAccountUseCase.execute().stream()
                .map(AccountDTOMapper::toAccountResponseDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        Account account = AccountDTOMapper.toAccount(accountRequestDTO);
        return AccountDTOMapper.toAccountResponseDTO(createAccountUseCase.execute(account));
    }

    public AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO) {
        Account account = AccountDTOMapper.toAccount(accountRequestDTO);
        return AccountDTOMapper.toAccountResponseDTO(updateAccountUseCase.execute(account));
    }

    public void deleteAccount(Integer accountId) {
        deleteAccountUseCase.execute(accountId);
    }


}
