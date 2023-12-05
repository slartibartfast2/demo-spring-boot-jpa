package ea.slartibartfast.demospringbootjpa.model.mapper;

import ea.slartibartfast.demospringbootjpa.model.dto.TransactionDto;
import ea.slartibartfast.demospringbootjpa.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Transaction toEntity(TransactionDto transactionDto);

    TransactionDto fromEntity(Transaction transaction);
}
