package com.devsu.bankaccount.infrastructure.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponseDTO {

    private Integer movementId;
    private Integer accountId;
    private LocalDateTime date;
    private String movementType;
    private BigDecimal value;
    private BigDecimal balance;
}
