package cloud.jordaan.juan.casino.transaction.data.r2dbc;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface PlayerTransactionRepository extends R2dbcRepository<PlayerTransaction, Long> {
    @Query("select t.* from player_transaction t, player p WHERE t.player_id = p.id and p.username = :username order by create_date desc limit 10 offset 0")
    Flux<PlayerTransaction> findByUsername(String username);
}