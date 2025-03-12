package com.devsu.bankaccount.infrastructure.account.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponseDTO {

    private Integer movementId;
    private Integer accountId;
    private String movementType;
    private BigDecimal value;
    private BigDecimal balance;
}
