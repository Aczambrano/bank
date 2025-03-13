package com.devsu.bankaccount.infrastructure.input.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    private Integer accountId;

    @NotBlank(message = "Account number cannot be blank")
    private String accountNumber;

    @NotBlank(message = "Account type cannot be blank")
    private String accountType;

    @NotNull(message = "Initial balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Initial balance must be a positive number")
    private BigDecimal initialBalance;

    @NotBlank(message = "Status cannot be null")
    private Boolean status;

    @NotNull(message = "Customer ID cannot be null")
    private Integer customerId;

    public AccountRequestDTO(String accountNumber, String accountType, BigDecimal initialBalance, Boolean status, Integer customerId) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.status = status;
        this.customerId = customerId;
    }
}
