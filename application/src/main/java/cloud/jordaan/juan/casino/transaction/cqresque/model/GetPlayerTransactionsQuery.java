package cloud.jordaan.juan.casino.transaction.cqresque.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerTransactionsQuery {
    String username;
}