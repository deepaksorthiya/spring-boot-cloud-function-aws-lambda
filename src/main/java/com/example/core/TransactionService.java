package com.example.core;

import com.example.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final UUID BROKER_ID = UUID.randomUUID();
    private final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public Optional<Transaction> writeTransaction(Transaction transaction) {
        try {
            Thread.sleep(100); //Simulates some intensive calculation
            transaction.transactionId = UUID.randomUUID();
            transaction.brokerId = BROKER_ID;

            return Optional.of(transaction);
        } catch (InterruptedException e) {
            logger.error("Error due to interruption while processing the transaction.", e);
            return Optional.empty();
        }
    }
}
