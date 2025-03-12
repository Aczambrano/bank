package com.devsu.bankaccount.infrastructure.account.output.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Integer movementId;

    private LocalDateTime date;

    @NotNull
    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @NotNull
    @Column(name = "value", nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

}
