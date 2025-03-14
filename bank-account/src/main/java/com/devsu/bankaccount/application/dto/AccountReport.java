package com.devsu.bankaccount.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountReport {
    private String accountNumber;
    private BigDecimal balance;
    private List<MovementReportDTO> movements;
}
