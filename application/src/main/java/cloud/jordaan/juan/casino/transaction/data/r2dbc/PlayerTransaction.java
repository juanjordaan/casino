package cloud.jordaan.juan.casino.transaction.data.r2dbc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Table
@Getter
@Setter
public class PlayerTransaction {
    @Id
    Long id;
    @Column
    Long playerId;
    @Column
    Instant createDate;
    @Column
    String transactionType;
    @Column
    BigDecimal amount;

    public PlayerTransaction() {

    }

    public PlayerTransaction(Long playerId, String transactionType, BigDecimal amount) {
        this.playerId = playerId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createDate = Instant.now();
    }
}
