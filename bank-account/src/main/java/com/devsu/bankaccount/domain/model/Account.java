package com.devsu.bankaccount.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Account {
    private Integer accountId;

    private String accountNumber;

    private String accountType;

    private BigDecimal initialBalance;

    private Boolean status;

    private Integer customerId;

    private List<Transaction> transactions;

    public Account(Integer accountId, String accountNumber, String accountType, BigDecimal initialBalance, Boolean status, Integer customerId) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.status = status;
        this.customerId = customerId;
    }

    public Account(Integer accountId, String accountNumber, String accountType, BigDecimal initialBalance, Boolean status, Integer customerId, List<Transaction> transactions) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.status = status;
        this.customerId = customerId;
        this.transactions = transactions;
    }

    public Account() {
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
