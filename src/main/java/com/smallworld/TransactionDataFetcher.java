package com.smallworld;

import com.smallworld.data.TransactionDto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionDataFetcher {

    public static void main(String[] args) {
//        List<TransactionDto> transactionDtoList = TransactionService.loadTransactionsFromJson();
//        TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();

//        System.out.println(transactionDataFetcher.getTotalTransactionAmount(transactionDtoList));
//        System.out.println(transactionDataFetcher.getTotalTransactionAmountSentBy(transactionDtoList,"Billy Kimber"));
//        System.out.println(transactionDataFetcher.getMaxTransactionAmount(transactionDtoList));
//        System.out.println(transactionDataFetcher.countUniqueClients(transactionDtoList));
//        System.out.println(transactionDataFetcher.hasOpenComplianceIssues(transactionDtoList,"Winston Churchill"));
//        System.out.println(transactionDataFetcher.getTransactionsByBeneficiaryName(transactionDtoList));
//        System.out.println(transactionDataFetcher.getUnsolvedIssueIds(transactionDtoList));
//        System.out.println(transactionDataFetcher.getAllSolvedIssueMessages(transactionDtoList));
//        System.out.println(transactionDataFetcher.getTop3TransactionsByAmount(transactionDtoList));
//        System.out.println(transactionDataFetcher.getTopSender(transactionDtoList));


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
                                && !transaction.getIssueSolved());

    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<TransactionDto>> getTransactionsByBeneficiaryName(List<TransactionDto> transactionDtoList) {

        return transactionDtoList.stream()
                .collect(Collectors.groupingBy(TransactionDto::getBeneficiaryFullName));
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Long> getUnsolvedIssueIds(List<TransactionDto> transactionDtoList) {
       return transactionDtoList.stream()
               .filter(transactionDto -> !transactionDto.getIssueSolved())
                .map(TransactionDto::getMtn)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages(List<TransactionDto> transactionDtoList) {
        return transactionDtoList.stream()
                .filter(TransactionDto::getIssueSolved)
                .map(TransactionDto::getIssueMessage)
                .collect(Collectors.toList());
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<TransactionDto> getTop3TransactionsByAmount(List<TransactionDto> transactionDtoList) {
        int limit = 3;
        transactionDtoList.sort(Comparator.comparingDouble(TransactionDto::getAmount).reversed());
        limit = Math.min(limit,transactionDtoList.size());
        return transactionDtoList.subList(0, limit);
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender(List<TransactionDto> transactionDtoList) {

        Map<String, Double> senderToTotalSentAmount = transactionDtoList.stream().collect(Collectors.groupingBy(TransactionDto::getSenderFullName,Collectors.summingDouble(TransactionDto::getAmount)));

        String topSender = "";
        double maxSentAmount = Integer.MIN_VALUE;

        for (Map.Entry<String, Double> entry : senderToTotalSentAmount.entrySet()) {
            if (entry.getValue() > maxSentAmount) {
                topSender = entry.getKey();
                maxSentAmount = entry.getValue();
            }
        }

        return Optional.ofNullable(topSender);
    }

}
