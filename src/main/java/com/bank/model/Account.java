package com.bank.model;

import java.math.BigDecimal;

public class Account {
    private long id;
    private long customerId;
    private String type;
    private BigDecimal balance;

    // Getters & Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public long getCustomerId() { return customerId; }
    public void setCustomerId(long customerId) { this.customerId = customerId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
