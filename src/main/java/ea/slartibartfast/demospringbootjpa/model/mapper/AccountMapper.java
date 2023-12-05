package ea.slartibartfast.demospringbootjpa.model.mapper;

import ea.slartibartfast.demospringbootjpa.model.dto.AccountDto;
import ea.slartibartfast.demospringbootjpa.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    Account toEntity(AccountDto accountDto);

    AccountDto fromEntity(Account account);

    @Mapping(target = "latestTransaction", ignore = true)
    AccountDto fromEntityIgnoreTransaction(Account account);
}

