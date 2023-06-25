package cloud.jordaan.juan.casino.players.cqresque.player.model;

public enum TransactionType {
	WAGER("WAGER"), WIN("WIN");

	String value;
	TransactionType(String value) {
		this.value = value;
	}
}