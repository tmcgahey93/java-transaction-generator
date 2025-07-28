package com.example.java_transaction_generator;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TransactionGenerator {

    public static Transaction generate() {
        Transaction txn = new Transaction();
        txn.transactionId = UUID.randomUUID().toString();
        txn.userId = "user-" + ThreadLocalRandom.current().nextInt(1, 100);
        txn.amount = ThreadLocalRandom.current().nextDouble(1.0, 5000.0);
        txn.currency = "USD";
        txn.location = "NY";
        txn.timeStamp = System.currentTimeMillis();
        return txn;

    }
    
}
