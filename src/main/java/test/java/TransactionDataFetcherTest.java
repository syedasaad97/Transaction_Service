package test.java;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.TransactionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class TransactionDataFetcherTest {

    private List<TransactionDto> transactionDtoList;

    private TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();



    @BeforeEach
    public void setup() {
        transactionDtoList = BaseTest.createTransactionList();
    }

    @Test
    void test_GetTotalTransactionAmount() {
        double totalAmount = transactionDataFetcher.getTotalTransactionAmount(transactionDtoList);
        Double expectedTotalAmount = 1250.0;
        assertEquals(expectedTotalAmount, totalAmount);
    }

    @Test
    void test_GetTotalTransactionAmountSentBy() {
        double amountSentByTom = transactionDataFetcher.getTotalTransactionAmountSentBy(transactionDtoList,"WXYZ");
        Double expectedAmountSentByWXYZ = 500.0;
        assertEquals(expectedAmountSentByWXYZ, amountSentByTom);
    }

    @Test
    void test_GetMaxTransactionAmount() {
        double maxAmount = transactionDataFetcher.getMaxTransactionAmount(transactionDtoList);
        double expectedMaxAmount = 600.0;
        assertEquals(expectedMaxAmount, maxAmount);
    }

    @Test
    void test_CountUniqueClients() {
        long uniqueClientCount = transactionDataFetcher.countUniqueClients(transactionDtoList);
        long expectedUniqueClientCount=6;
        assertEquals(expectedUniqueClientCount, uniqueClientCount);
    }

    @Test
    void test_HasOpenComplianceIssues() {
        boolean hasOpenIssues = transactionDataFetcher.hasOpenComplianceIssues(transactionDtoList,"WXYZ");
        boolean expectedHasOpenIssues = true;
        assertEquals(expectedHasOpenIssues,hasOpenIssues);
    }

    @Test
    void test_GetTransactionsByBeneficiaryName() {
        Map<String, List<TransactionDto>> transactionsByBeneficiary = transactionDataFetcher.getTransactionsByBeneficiaryName(transactionDtoList);
        assertNotNull(transactionsByBeneficiary);
        int expectedTransactionCountForBeneficiary=2;
        assertEquals(expectedTransactionCountForBeneficiary, transactionsByBeneficiary.get("ABC").size());
    }

    @Test
    void test_GetUnsolvedIssueIds() {
        Set<Long> unsolvedIssueIds = transactionDataFetcher.getUnsolvedIssueIds(transactionDtoList);
        assertNotNull(unsolvedIssueIds);
    }

    @Test
    void test_GetAllSolvedIssueMessages() {
        List<String> solvedIssueMessages = transactionDataFetcher.getAllSolvedIssueMessages(transactionDtoList);
        String expectedSolvedIssueMessage = "Test Message 2";
        assertTrue(solvedIssueMessages.contains(expectedSolvedIssueMessage));
    }

    @Test
    void test_GetTop3TransactionsByAmount() {
        List<TransactionDto> top3Transactions = transactionDataFetcher.getTop3TransactionsByAmount(transactionDtoList);

        assertEquals(3, top3Transactions.size());
        assertEquals(600,top3Transactions.get(0).getAmount());
    }

    @Test
    void test_GetTopSender() {
        Optional<String> topSender = transactionDataFetcher.getTopSender(transactionDtoList);
        String expectedTopSenderName = "PXYZ";
        assertTrue(topSender.isPresent());
        assertEquals(expectedTopSenderName, topSender.get());
    }

}
