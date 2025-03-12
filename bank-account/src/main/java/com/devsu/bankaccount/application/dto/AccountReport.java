package com.devsu.bankaccount.application.dto;

import com.devsu.bankaccount.domain.model.Movement;
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
    private BigDecimal initialBalance;
    private List<Movement> movements;
}
