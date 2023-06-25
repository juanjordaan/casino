package cloud.jordaan.juan.casino.transaction.cqresque.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class CreateTransactionResponse {
    Long id;
    Long playerId;
    Instant createDate;
    String transactionType;
    BigDecimal balance;
}
