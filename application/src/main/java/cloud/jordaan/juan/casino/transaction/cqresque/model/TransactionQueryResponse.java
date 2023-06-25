package cloud.jordaan.juan.casino.transaction.cqresque.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionQueryResponse {
    Long transactionId;
    String transactionType;
    BigDecimal amount;
}