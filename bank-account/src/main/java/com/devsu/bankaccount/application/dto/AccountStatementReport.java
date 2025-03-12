package com.devsu.bankaccount.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatementReport {
    private Integer customerId;
    private List<AccountReport> accounts;
}
