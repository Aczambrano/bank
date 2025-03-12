package com.devsu.bankaccount.infrastructure.account.input.controller;

import com.devsu.bankaccount.infrastructure.account.input.dto.AccountRequestDTO;
import com.devsu.bankaccount.infrastructure.account.input.dto.AccountResponseDTO;
import com.devsu.bankaccount.infrastructure.account.input.handler.AccountHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cuentas")
public class AccountController {

    private final AccountHandler accountHandler;

    public AccountController(AccountHandler accountHandler) {
        this.accountHandler = accountHandler;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAll() {
        return new ResponseEntity<>(accountHandler.getAccounts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO response = accountHandler.createAccount(accountRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO response = accountHandler.updateAccount(accountRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer accountId) {
        accountHandler.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
