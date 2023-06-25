package cloud.jordaan.juan.casino.balance.cqresque;

import cloud.jordaan.juan.casino.balance.cqresque.model.UpdateBalanceCommand;
import cloud.jordaan.juan.casino.error.ClientApplicationException;
import cloud.jordaan.juan.casino.players.data.r2dbc.PlayerRepository;
import cloud.jordaan.juan.casino.transaction.cqresque.TransactionCommandService;
import cloud.jordaan.juan.casino.transaction.cqresque.model.CreateTransactionCommand;
import org.springframework.stereotype.Service;

import cloud.jordaan.juan.casino.balance.cqresque.model.UpdateBalanceResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class BalanceCommandService {
	final PlayerRepository playerRepository;
	final TransactionCommandService playerTransactionService;

	public BalanceCommandService(PlayerRepository playerRepository, TransactionCommandService playerTransactionService) {
		this.playerRepository = playerRepository;
		this.playerTransactionService = playerTransactionService;
	}

	public Mono<UpdateBalanceResponse> update(UpdateBalanceCommand command) {
		return Mono.just(command)
				.map(BalanceServiceMapper.INSTANCE::mapToCreateTransaction)
				.flatMap(playerTransactionService::create)
				.flatMap(t -> playerRepository.findById(command.getPlayerId()))
				.switchIfEmpty(Mono.defer(() -> Mono.error(new ClientApplicationException("Player does not exist : " + command.getPlayerId()))))
				.map(BalanceServiceMapper.INSTANCE::mapToUpdateBalance);
	}
}