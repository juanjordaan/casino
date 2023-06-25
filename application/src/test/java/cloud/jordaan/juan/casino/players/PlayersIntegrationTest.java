package cloud.jordaan.juan.casino.players;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import cloud.jordaan.juan.casino.CasinoApplication;
import cloud.jordaan.juan.casino.balance.cqresque.model.UpdateBalanceCommand;

import javax.validation.constraints.AssertTrue;
import java.util.Collections;

@ActiveProfiles("test")
@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes=CasinoApplication.class)
public class PlayersIntegrationTest {
	@Autowired
	WebTestClient webTestClient;

	@Test
	void contextLoads() {
		assert true;
	}

	// TODO - Make H2 in mem with liquibase and r2dbc
	/*@Test
	void getPlayerBalance_Passes() {
		webTestClient
			.get()
			.uri("/player/{playerId}/balance", 1L)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.consumeWith(response -> {
					Assertions.assertNotNull(response.getResponseBody());
			});
	}

	@Test
	void updatePlayerBalance_Passes() {
		webTestClient
			.post()
			.uri("/player/{playerId}/balance/update", 1L)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(new UpdateBalanceCommand()))
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.consumeWith(response -> {
				Assertions.assertNotNull(response.getResponseBody());
			});
	}*/
}