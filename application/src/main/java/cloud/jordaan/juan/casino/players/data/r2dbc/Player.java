package cloud.jordaan.juan.casino.players.data.r2dbc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table
@Getter
@Setter
public class Player {
    @Id
    Long id;
    @Column
    String username;
    @Column
    BigDecimal balance;
}