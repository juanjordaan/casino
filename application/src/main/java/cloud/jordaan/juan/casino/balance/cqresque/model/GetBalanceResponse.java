package cloud.jordaan.juan.casino.balance.cqresque.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBalanceResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	Long playerId;
	BigDecimal balance;
}