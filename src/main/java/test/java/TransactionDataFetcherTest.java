package test.java;

import com.smallworld.data.Transaction;
import com.smallworld.data.TransactionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TransactionDataFetcherTest {

    private List<TransactionDto> transactionDtoList;


    @BeforeEach
    public void setup() {

        transactionDtoList = Transaction.loadTransactionsFromJson();
    }

    @Test
    void testGetTotalTransactionAmount() {
//        double totalAmount = transactionDataFetcher.getTotalTransactionAmount();
//        assertEquals(expectedTotalAmount, totalAmount);
    }

}
