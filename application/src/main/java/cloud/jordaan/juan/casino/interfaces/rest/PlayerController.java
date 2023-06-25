package cloud.jordaan.juan.casino.interfaces.rest;

import cloud.jordaan.juan.casino.balance.cqresque.model.GetBalanceResponse;
import cloud.jordaan.juan.casino.balance.cqresque.model.UpdateBalanceResponse;
import cloud.jordaan.juan.casino.error.ClientApplicationException;
import cloud.jordaan.juan.casino.players.cqresque.player.PlayerCommandService;
import cloud.jordaan.juan.casino.players.cqresque.player.PlayerQueryService;
import cloud.jordaan.juan.casino.players.cqresque.player.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.jordaan.juan.casino.balance.cqresque.BalanceCommandService;
import cloud.jordaan.juan.casino.balance.cqresque.BalanceQueryService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/player")
public class PlayerController {
	final BalanceQueryService balanceQueryService;
	final BalanceCommandService balanceCommandService;
	final PlayerCommandService playerCommandService;
	final PlayerQueryService playerQueryService;
	PlayerController(BalanceQueryService balanceQueryService, BalanceCommandService balanceCommandService, PlayerCommandService playerCommandService, PlayerQueryService playerQueryService) {
		this.balanceQueryService = balanceQueryService;
		this.balanceCommandService = balanceCommandService;
		this.playerCommandService = playerCommandService;
		this.playerQueryService = playerQueryService;
	}

	@GetMapping(value="/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<GetPlayerResponse>> getPlayer(@PathVariable("playerId") Long playerId) {
		return Mono.just(playerId)
				.flatMap(playerQueryService::getPlayer)
				.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
				;
	}

	@PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<CreatePlayerResponse>> createPlayer(@RequestBody CreatePlayerCommand command) {
		return Mono.just(command)
				.flatMap(playerCommandService::createPlayer)
				.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
				;
	}

	@GetMapping(value="/{playerId}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<GetBalanceResponse>> getPlayerBalance(@PathVariable("playerId") Long playerId) {
		return Mono.just(playerId)
				.flatMap(balanceQueryService::getPlayerBalance)
				.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
				;
	}

	@PostMapping(value="/{playerId}/balance/update", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<UpdateBalanceResponse>> updatePlayerBalance(@PathVariable("playerId") Long playerId, @Valid @RequestBody UpdateBalanceRequest request) {
		return Mono.just(PlayerRestMapper.INSTANCE.map(playerId, request))
				.flatMap(balanceCommandService::update)
				.map(t -> new ResponseEntity<>(t, HttpStatus.OK))
				;
	}
}