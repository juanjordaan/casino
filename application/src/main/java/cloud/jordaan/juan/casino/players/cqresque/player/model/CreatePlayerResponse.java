package cloud.jordaan.juan.casino.players.cqresque.player.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreatePlayerResponse {
    Long id;
    String username;
    BigDecimal balance;
}
