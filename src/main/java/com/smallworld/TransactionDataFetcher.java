package com.smallworld;

import com.smallworld.data.Transaction;
import com.smallworld.data.TransactionDto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.smallworld.data.Transaction.loadTransactionsFromJson;

public class TransactionDataFetcher {

    public static void main(String[] args) {
        List<TransactionDto> transactionDtoList = Transaction.loadTransactionsFromJson();
        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

        System.out.println(transactionDataFetcher.getTotalTransactionAmount(transactionDtoList));
    }
    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount(List<TransactionDto> transactionDtoList) {
        return transactionDtoList.stream()
                .mapToDouble(TransactionDto::getAmount)
                .sum();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(List<TransactionDto> transactionDtoList,String senderFullName) {
        return transactionDtoList.stream()
                .filter(transaction -> transaction.getSenderFullName().equals(senderFullName))
                .mapToDouble(TransactionDto::getAmount)
                .sum();
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount(List<TransactionDto> transactionDtoList) {
        return transactionDtoList.stream()
                .mapToDouble(TransactionDto::getAmount)
                .max()
                .orElse(0.0);
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients(List<TransactionDto> transactionDtoList) {
        Set<String> uniqueClients = transactionDtoList.stream()
                .flatMap(transaction -> Stream.of(transaction.getSenderFullName(), transaction.getBeneficiaryFullName()))
                .collect(Collectors.toSet());
        return uniqueClients.size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(List<TransactionDto> transactionDtoList,String clientFullName) {
        return transactionDtoList.stream()
                .anyMatch(transaction ->
                        (transaction.getSenderFullName().equals(clientFullName) || transaction.getBeneficiaryFullName().equals(clientFullName))
                                && transaction.getIssueSolved() == false);

    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<TransactionDto>> getTransactionsByBeneficiaryName(List<TransactionDto> transactionDtoList) {
        Map<String , List<TransactionDto>> transactionMap = new HashMap<>();
        for (TransactionDto transction:transactionDtoList
             ) {

            if(transactionMap.containsKey(transction.getBeneficiaryFullName())){
                transactionMap.get(transction.getBeneficiaryFullName()).add(transction);
//                transactionMap.put(transction.getBeneficiaryFullName(),);
            }else{
                transactionMap.put(transction.getBeneficiaryFullName(),Arrays.asList(transction));
            }

        }
        return transactionMap;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds(List<TransactionDto> transactionDtoList) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages(List<TransactionDto> transactionDtoList) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount(List<TransactionDto> transactionDtoList) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender(List<TransactionDto> transactionDtoList) {
        throw new UnsupportedOperationException();
    }

}
