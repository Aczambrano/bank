package com.devsu.bankaccount.infrastructure.account.output.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @NotBlank
    @Size(min = 10, max = 20)
    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @NotBlank
    @Column(name = "account_type", nullable = false)
    private String accountType;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "initial_balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal initialBalance;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    @NotNull
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<MovementEntity> movements;

    public AccountEntity(Integer accountId) {
        this.accountId = accountId;
    }
}
