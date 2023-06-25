package cloud.jordaan.juan.casino.balance.cqresque.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class UpdateBalanceResponse {
    String transactionId;
    BigInteger balance;
}