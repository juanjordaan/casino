package cloud.jordaan.juan.casino.transaction.data.r2dbc;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface PlayerTransactionRepository extends R2dbcRepository<PlayerTransaction, Long> {
    @Query("select t.* from player_transaction t, player p WHERE t.player_id = p.id and p.username = :username")
    Flux<PlayerTransaction> findByUsername(String username);
}