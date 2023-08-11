package com.smallworld.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    // Represent your transaction data here.

    private TransactionService(){}

    private static List<TransactionDto> transactionDtoLists = new ArrayList<>();

    public static List<TransactionDto> loadTransactionsFromJson() {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                transactionDtoLists = objectMapper.readValue(new File("transactions.json"), new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return transactionDtoLists;

    }
}
