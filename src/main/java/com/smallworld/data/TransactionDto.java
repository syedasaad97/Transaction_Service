package com.smallworld.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionDto {

    private Long mtn;

    private Double amount;

    private String senderFullName;

    private Integer senderAge;

    private String beneficiaryFullName;

    private Integer beneficiaryAge;

    private Integer issueId;

    private Boolean issueSolved;

    private String issueMessage;

}
