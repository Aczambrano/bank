package com.devsu.bankaccount.application.port.input.report;

import com.devsu.bankaccount.application.dto.AccountReport;
import com.devsu.bankaccount.application.dto.AccountStatementReport;
import com.devsu.bankaccount.application.port.ouput.IAccountRepository;
import com.devsu.bankaccount.application.port.ouput.IMovementrepository;
import com.devsu.bankaccount.domain.model.Account;
import com.devsu.bankaccount.domain.model.Movement;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenerateReportUseCase {

    private final IAccountRepository accountRepository;
    private final IMovementrepository movementRepository;


    public GenerateReportUseCase(IAccountRepository accountRepository, IMovementrepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }


    public AccountStatementReport execute(Integer customerId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);

        List<AccountReport> accountReports = accounts.stream().map(account -> {
            List<Movement> movements = movementRepository.findByAccount_AccountIdAndDateBetween(
                    account.getAccountId(), startDate, endDate
            );

            return new AccountReport(account.getAccountNumber(), account.getInitialBalance(), movements);
        }).collect(Collectors.toList());

        return new AccountStatementReport(customerId, accountReports);
    }
}
