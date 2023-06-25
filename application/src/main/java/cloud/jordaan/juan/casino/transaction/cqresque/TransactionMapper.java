package cloud.jordaan.juan.casino.transaction.cqresque;

import cloud.jordaan.juan.casino.transaction.cqresque.model.CreateTransactionResponse;
import cloud.jordaan.juan.casino.transaction.cqresque.model.TransactionQueryResponse;
import cloud.jordaan.juan.casino.transaction.data.r2dbc.PlayerTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    CreateTransactionResponse mapToCreateTransaction(PlayerTransaction playerTransaction);

    @Mapping(source = "id", target = "transactionId")
    TransactionQueryResponse mapToTransactionQuery(PlayerTransaction playerTransaction);
}