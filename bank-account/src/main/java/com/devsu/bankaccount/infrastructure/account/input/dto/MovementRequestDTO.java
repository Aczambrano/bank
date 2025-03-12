package com.devsu.bankaccount.infrastructure.account.input.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class MovementRequestDTO {
    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;

    @NotNull(message = "Movement type cannot be null")
    @Pattern(regexp = "Deposit|Withdrawal", message = "Type movement must be Deposit, Withdrawal")
    private String movementType;

    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Value must be positive")
    private BigDecimal value;

    private BigDecimal balance;

    private LocalDateTime date;
}
