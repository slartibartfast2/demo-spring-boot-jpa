package ea.slartibartfast.demospringbootjpa.service;

import ea.slartibartfast.demospringbootjpa.model.dto.TransactionDto;
import ea.slartibartfast.demospringbootjpa.model.entity.Account;
import ea.slartibartfast.demospringbootjpa.model.entity.Transaction;
import ea.slartibartfast.demospringbootjpa.model.entity.TransactionStatus;
import ea.slartibartfast.demospringbootjpa.model.exception.EntityCannotPersistedException;
import ea.slartibartfast.demospringbootjpa.model.exception.InsufficientBalanceException;
import ea.slartibartfast.demospringbootjpa.model.mapper.TransactionMapper;
import ea.slartibartfast.demospringbootjpa.repository.AccountRepository;
import ea.slartibartfast.demospringbootjpa.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static ea.slartibartfast.demospringbootjpa.model.entity.TransactionDirection.CREDIT;
import static ea.slartibartfast.demospringbootjpa.model.entity.TransactionDirection.DEBIT;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionDto getTransaction(String externalId) {
        return transactionRepository.findTransactionByExternalId(UUID.fromString(externalId))
                                .map(transactionMapper::fromEntity)
                                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        var transaction = transactionRepository.save(transactionMapper.toEntity(transactionDto));
        log.info("new transaction created on DB");
        var account = updateAccountBalance(transaction);
        log.info("account balance updated, new balance is {}", account.getBalance());
        return transactionMapper.fromEntity(transaction);
    }

    private Account updateAccountBalance(Transaction transaction) {
        var account = accountRepository.findAccountByExternalId(transaction.getAccountExternalId()).orElseThrow(EntityNotFoundException::new);
        if (CREDIT.equals(transaction.getDirection())) {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
        } else if (DEBIT.equals(transaction.getDirection())) {
            if (account.getBalance().compareTo(transaction.getAmount()) < 0) throw new InsufficientBalanceException();
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        }

        return account;
    }

    public TransactionDto updateTransactionStatus(String externalId, TransactionStatus newStatus) {
        var transaction = transactionRepository.findTransactionByExternalId(UUID.fromString(externalId)).orElseThrow(EntityNotFoundException::new);
        transaction.setTransactionStatus(newStatus);
        return Optional.of(transactionRepository.save(transaction))
                       .map(transactionMapper::fromEntity)
                       .orElseThrow(EntityCannotPersistedException::new);
    }
}
