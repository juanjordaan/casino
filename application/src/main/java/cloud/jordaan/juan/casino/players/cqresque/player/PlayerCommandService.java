package cloud.jordaan.juan.casino.players.cqresque.player;

import cloud.jordaan.juan.casino.players.cqresque.player.model.CreatePlayerCommand;
import cloud.jordaan.juan.casino.players.cqresque.player.model.CreatePlayerResponse;
import cloud.jordaan.juan.casino.players.data.r2dbc.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PlayerCommandService {
    final PlayerRepository repository;

    public PlayerCommandService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Mono<CreatePlayerResponse> createPlayer(CreatePlayerCommand command) {
        return Mono.just(command)
                .map(PlayerServiceMapper.INSTANCE::map)
                .flatMap(repository::save)
                .map(PlayerServiceMapper.INSTANCE::mapToCreatePlayer);
    }
}