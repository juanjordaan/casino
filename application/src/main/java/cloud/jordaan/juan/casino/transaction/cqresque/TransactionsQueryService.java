package cloud.jordaan.juan.casino.transaction.cqresque;

import cloud.jordaan.juan.casino.error.ClientApplicationException;
import cloud.jordaan.juan.casino.error.InternalApplicationException;
import cloud.jordaan.juan.casino.players.data.r2dbc.PlayerRepository;
import cloud.jordaan.juan.casino.transaction.cqresque.model.TransactionQueryResponse;
import cloud.jordaan.juan.casino.transaction.cqresque.model.GetPlayerTransactionsQuery;
import cloud.jordaan.juan.casino.transaction.data.r2dbc.PlayerTransactionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TransactionsQueryService {
    final PlayerRepository playerRepository;
    final PlayerTransactionRepository transactionRepository;

    public TransactionsQueryService(PlayerRepository playerRepository, PlayerTransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Flux<TransactionQueryResponse> getPlayerTransactions(GetPlayerTransactionsQuery query) {
        return Flux.just(query.getUsername())
                .flatMap(transactionRepository::findByUsername)
                .map(TransactionMapper.INSTANCE::mapToTransactionQuery);
    }
}