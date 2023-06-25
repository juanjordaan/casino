package cloud.jordaan.juan.casino.interfaces.rest;

import cloud.jordaan.juan.casino.error.ClientApplicationException;
import cloud.jordaan.juan.casino.transaction.cqresque.TransactionsQueryService;
import cloud.jordaan.juan.casino.transaction.cqresque.model.GetPlayerTransactionsQuery;
import cloud.jordaan.juan.casino.transaction.cqresque.model.TransactionQueryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/admin")
public class BackOfficeController {
    final TransactionsQueryService queryService;

    public BackOfficeController(TransactionsQueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping(value="/player/transactions", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TransactionQueryResponse> updatePlayerBalance(@Valid @RequestBody GetPlayerTransactionsQuery query) {
        return Flux.just(query)
                .flatMap(queryService::getPlayerTransactions)
//                .defaultIfEmpty(throw new ClientApplicationException("Username does not exist : " + query.getUsername());)
                ;
    }
}