package com.smallworld.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    // Represent your transaction data here.

    private static List<TransactionDto> transactionDtoLists = new ArrayList<>();

    private Transaction(){}

    public static List<TransactionDto> loadTransactionsFromJson() {
        if(!transactionDtoLists.isEmpty())
            return transactionDtoLists;
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                transactionDtoLists = objectMapper.readValue(new File("transactions.json"), new TypeReference<List<TransactionDto>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return transactionDtoLists;
        }

    }
}
