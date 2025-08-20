package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Transaction(
        String stockId,
        Integer quantity,
        UUID brokerId,
        UUID transactionId) {

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
