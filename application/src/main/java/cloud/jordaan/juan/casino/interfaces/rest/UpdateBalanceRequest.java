package cloud.jordaan.juan.casino.interfaces.rest;

import java.math.BigDecimal;

import cloud.jordaan.juan.casino.players.cqresque.player.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateBalanceRequest {
	@NotNull(message = "Amount is mandatory")
	BigDecimal amount;
	@NotNull(message = "TransactionType is mandatory")
	TransactionType transactionType;
}