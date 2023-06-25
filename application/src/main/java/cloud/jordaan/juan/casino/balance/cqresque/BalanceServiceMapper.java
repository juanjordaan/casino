package cloud.jordaan.juan.casino.balance.cqresque;

import cloud.jordaan.juan.casino.balance.cqresque.model.GetBalanceResponse;
import cloud.jordaan.juan.casino.balance.cqresque.model.UpdateBalanceCommand;
import cloud.jordaan.juan.casino.balance.cqresque.model.UpdateBalanceResponse;
import cloud.jordaan.juan.casino.players.data.r2dbc.Player;
import cloud.jordaan.juan.casino.transaction.cqresque.model.CreateTransactionCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
interface BalanceServiceMapper {
    BalanceServiceMapper INSTANCE = Mappers.getMapper(BalanceServiceMapper.class);

    @Mapping(source="id", target="playerId")
    GetBalanceResponse mapToGetBalance(Player player);

    UpdateBalanceResponse mapToUpdateBalance(Player player);

    CreateTransactionCommand mapToCreateTransaction(UpdateBalanceCommand commands);
}