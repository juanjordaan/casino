package cloud.jordaan.juan.casino.transaction.cqresque;

import cloud.jordaan.juan.casino.error.ClientApplicationException;
import cloud.jordaan.juan.casino.error.TeapotApplicationException;
import cloud.jordaan.juan.casino.players.data.r2dbc.PlayerRepository;
import cloud.jordaan.juan.casino.transaction.cqresque.model.CreateTransactionCommand;
import cloud.jordaan.juan.casino.transaction.cqresque.model.CreateTransactionResponse;
import cloud.jordaan.juan.casino.transaction.data.r2dbc.PlayerTransaction;
import cloud.jordaan.juan.casino.transaction.data.r2dbc.PlayerTransactionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class TransactionCommandService {
    final PlayerRepository playerRepository;
    final PlayerTransactionRepository transactionRepository;

    public TransactionCommandService(PlayerRepository playerRepository, PlayerTransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Mono<CreateTransactionResponse> create(CreateTransactionCommand command) {
        return Mono.just(command)
                .flatMap(t -> {
                    if(t.getTransactionType().equalsIgnoreCase("WAGER")) {
                        return doWager(t.getPlayerId(), t.getAmount());
                    } else if(t.getTransactionType().equalsIgnoreCase("WIN")) {
                        return doWin(t.getPlayerId(), t.getAmount());
                    } else {
                        throw new ClientApplicationException("Unknown transactionType : " + t.getTransactionType());
                    }
                });
    }

    private Mono<? extends CreateTransactionResponse> doWin(Long playerId, BigDecimal amount) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ClientApplicationException("Player does not exist : " + playerId))))
                .map(t -> {
                    t.setBalance(t.getBalance().add(amount));
                    return t;
                })
                .flatMap(playerRepository::save)
                .map(t -> new PlayerTransaction(playerId, "WAGER", amount))
                .flatMap(transactionRepository::save)
                .map(TransactionMapper.INSTANCE::mapToCreateTransaction);
    }

    private Mono<CreateTransactionResponse> doWager(Long playerId, BigDecimal amount) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ClientApplicationException("Player does not exist : " + playerId))))
                .map(t -> {
                    if(t.getBalance().compareTo(amount) == -1) {
                        throw new TeapotApplicationException("Not enough balance available");
                    }
                    t.setBalance(t.getBalance().subtract(amount));
                    return t;
                })
                .flatMap(playerRepository::save)
                .map(t -> new PlayerTransaction(playerId, "WAGER", amount))
                .flatMap(transactionRepository::save)
                .map(TransactionMapper.INSTANCE::mapToCreateTransaction);
    }
}