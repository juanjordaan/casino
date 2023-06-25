package cloud.jordaan.juan.casino.interfaces.rest;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cloud.jordaan.juan.casino.balance.cqresque.model.UpdateBalanceCommand;

@Mapper
interface PlayerRestMapper {
	PlayerRestMapper INSTANCE = Mappers.getMapper(PlayerRestMapper.class);
	
	UpdateBalanceCommand map(Long playerId, UpdateBalanceRequest request);
}
