package ea.slartibartfast.demospringbootjpa.service;

import ea.slartibartfast.demospringbootjpa.model.dto.AccountDto;
import ea.slartibartfast.demospringbootjpa.model.exception.EntityCannotPersistedException;
import ea.slartibartfast.demospringbootjpa.model.mapper.AccountMapper;
import ea.slartibartfast.demospringbootjpa.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto getAccount(String externalId) {
        return accountRepository.findAccountWithLatestTransactionByExternalId(UUID.fromString(externalId))
                                .map(accountMapper::fromEntity)
                                .orElseThrow(EntityNotFoundException::new);
    }

    public AccountDto createAccount(AccountDto accountDto) {
        return Optional.of(accountRepository.save(accountMapper.toEntity(accountDto)))
                       .map(accountMapper::fromEntityIgnoreTransaction)
                       .orElseThrow(EntityCannotPersistedException::new);
    }

    public AccountDto updateAccount(AccountDto accountDto) {
        var account = accountRepository.findAccountByExternalId(UUID.fromString(accountDto.getExternalId())).orElseThrow(EntityNotFoundException::new);
        account.setName(accountDto.getName());
        log.info("Account new name: {}", account.getName());
        return Optional.of(accountRepository.save(account)).map(accountMapper::fromEntityIgnoreTransaction).orElseThrow(EntityCannotPersistedException::new);
    }

    public AccountDto deleteAccount(String externalId) {
        var account = accountRepository.findAccountByExternalId(UUID.fromString(externalId)).orElseThrow(EntityNotFoundException::new);
        account.setDeletedAt(LocalDateTime.now());
        log.info("Account {} deleted", account.getExternalId());
        return Optional.of(accountRepository.save(account)).map(accountMapper::fromEntity).orElseThrow(EntityCannotPersistedException::new);
    }
}
