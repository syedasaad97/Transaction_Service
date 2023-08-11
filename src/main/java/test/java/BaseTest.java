package test.java;

import com.smallworld.data.TransactionDto;

import java.util.Arrays;
import java.util.List;

public final class BaseTest {

    private BaseTest() {
    }

    public static List<TransactionDto> createTransactionList(){
        return Arrays.asList(createTransactions1(),createTransactions2(),createTransactions3(),createTransactions4());
    }

    private static TransactionDto createTransactions1() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMtn(123456L);
        transactionDto.setAmount(600.0);
        transactionDto.setBeneficiaryFullName("ABC");
        transactionDto.setBeneficiaryAge(21);
        transactionDto.setSenderFullName("PXYZ");
        transactionDto.setSenderAge(12);
        transactionDto.setIssueId(123);
        transactionDto.setIssueSolved(true);
        transactionDto.setIssueMessage("Test Message");
        return transactionDto;
    }

    private static TransactionDto createTransactions2() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMtn(123457L);
        transactionDto.setAmount(300.0);
        transactionDto.setBeneficiaryFullName("ABCF");
        transactionDto.setBeneficiaryAge(21);
        transactionDto.setSenderFullName("WXYZ");
        transactionDto.setSenderAge(12);
        transactionDto.setIssueId(null);
        transactionDto.setIssueSolved(true);
        transactionDto.setIssueMessage("Test Message 2");
        return transactionDto;
    }
    private static TransactionDto createTransactions3() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMtn(123458L);
        transactionDto.setAmount(200.0);
        transactionDto.setBeneficiaryFullName("ABCE");
        transactionDto.setBeneficiaryAge(21);
        transactionDto.setSenderFullName("WXYZ");
        transactionDto.setSenderAge(12);
        transactionDto.setIssueId(124);
        transactionDto.setIssueSolved(false);
        transactionDto.setIssueMessage("Test Message 3");
        return transactionDto;
    }

    private static TransactionDto createTransactions4() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMtn(123459L);
        transactionDto.setAmount(150.0);
        transactionDto.setBeneficiaryFullName("ABC");
        transactionDto.setBeneficiaryAge(21);
        transactionDto.setSenderFullName("XYZ");
        transactionDto.setSenderAge(12);
        transactionDto.setIssueId(null);
        transactionDto.setIssueSolved(true);
        transactionDto.setIssueMessage("");
        return transactionDto;
    }
}
