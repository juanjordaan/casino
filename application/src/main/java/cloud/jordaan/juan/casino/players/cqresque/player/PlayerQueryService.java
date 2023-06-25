package cloud.jordaan.juan.casino.players.cqresque.player;

import cloud.jordaan.juan.casino.players.cqresque.player.model.GetPlayerResponse;
import cloud.jordaan.juan.casino.players.data.r2dbc.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PlayerQueryService {
    final PlayerRepository repository;

    public PlayerQueryService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Mono<GetPlayerResponse> getPlayer(Long id) {
        return Mono.just(id)
                .flatMap(repository::findById)
                .map(PlayerServiceMapper.INSTANCE::mapToGetPlayer);
    }
}