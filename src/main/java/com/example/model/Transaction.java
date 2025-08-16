package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    public String stockId;
    public Integer quantity;
    public UUID brokerId;
    public UUID transactionId;

    public Boolean isValid() {
        return (stockId != null && !stockId.isBlank() && quantity != null && quantity > 0);
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "stockId='" + stockId + '\'' +
               ", quantity=" + quantity +
               ", brokerId=" + brokerId +
               ", transactionId=" + transactionId +
               '}';
    }
}
