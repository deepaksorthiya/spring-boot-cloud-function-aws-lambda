package com.example.core;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UnicornStockBrokerHandler implements Function<Transaction, APIGatewayProxyResponseEvent> {

    private final TransactionService transactionService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UnicornStockBrokerHandler.class);
    private final ObjectMapper objectMapper;

    public UnicornStockBrokerHandler(TransactionService transactionService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    public APIGatewayProxyResponseEvent apply(final Transaction transaction) {
        LOGGER.info("Transaction Processing: {}", transaction);
        if (!transaction.isValid()) {
            return createAPIGwResponse(400, "Invalid request body.");
        }

        var transactionResponse = transactionService.writeTransaction(transaction);
        return transactionResponse
                .map(it -> createAPIGwResponse(200, generateJSONResponse(it)))
                .orElseGet(() -> createAPIGwResponse(500, "Unexpected error while writing transaction."));

    }

    private APIGatewayProxyResponseEvent createAPIGwResponse(Integer statusCode, String message) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(statusCode)
                .withBody(String.format("%s%n", message));
    }

    private String generateJSONResponse(Transaction transaction) {
        try {
            String json = objectMapper.writeValueAsString(transaction);
            LOGGER.info("Transaction JSON: {}", json);
            LOGGER.info("Transaction Completed ....");
            return json;
        } catch (JsonProcessingException e) {
            LOGGER.error("Transaction JSON processing error", e);
        }
        return null;
    }

}
