package cloud.jordaan.juan.casino.balance.cqresque.model;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBalanceCommand {
	Long playerId;
	BigInteger amount;
	String transactionType;
}