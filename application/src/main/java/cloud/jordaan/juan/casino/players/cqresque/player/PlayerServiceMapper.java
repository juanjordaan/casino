package cloud.jordaan.juan.casino.players.cqresque.player;

import cloud.jordaan.juan.casino.players.cqresque.player.model.CreatePlayerCommand;
import cloud.jordaan.juan.casino.players.cqresque.player.model.CreatePlayerResponse;
import cloud.jordaan.juan.casino.players.cqresque.player.model.GetPlayerResponse;
import cloud.jordaan.juan.casino.players.data.r2dbc.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface PlayerServiceMapper {
    PlayerServiceMapper INSTANCE = Mappers.getMapper(PlayerServiceMapper.class);

    Player map(CreatePlayerCommand command);
    CreatePlayerResponse mapToCreatePlayer(Player player);
    GetPlayerResponse mapToGetPlayer(Player player);
}
