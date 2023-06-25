package cloud.jordaan.juan.casino.balance.cqresque;

import cloud.jordaan.juan.casino.error.ClientApplicationException;
import cloud.jordaan.juan.casino.players.data.r2dbc.PlayerRepository;
import org.springframework.stereotype.Service;

import cloud.jordaan.juan.casino.balance.cqresque.model.GetBalanceResponse;
import reactor.core.publisher.Mono;

@Service
public class BalanceQueryService {
	final PlayerRepository playerRepository;

	public BalanceQueryService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Mono<GetBalanceResponse> getPlayerBalance(Long playerId) {
		return Mono.just(playerId)
				.flatMap(playerRepository::findById)
				.switchIfEmpty(Mono.defer(() -> Mono.error(new ClientApplicationException("Player does not exist : " + playerId))))
				.map(BalanceServiceMapper.INSTANCE::mapToGetBalance);
	}
}