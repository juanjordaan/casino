package cloud.jordaan.juan.casino.players.cqresque.player.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GetPlayerResponse {
    Long id;
    String username;
    BigDecimal balance;
}