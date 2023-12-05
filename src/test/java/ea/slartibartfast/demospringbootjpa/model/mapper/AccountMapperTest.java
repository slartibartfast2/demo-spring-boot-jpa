package ea.slartibartfast.demospringbootjpa.model.mapper;

import ea.slartibartfast.demospringbootjpa.model.dto.AccountDto;
import ea.slartibartfast.demospringbootjpa.model.dto.TransactionDto;
import ea.slartibartfast.demospringbootjpa.model.entity.Account;
import ea.slartibartfast.demospringbootjpa.model.entity.Transaction;
import ea.slartibartfast.demospringbootjpa.model.entity.TransactionDirection;
import ea.slartibartfast.demospringbootjpa.model.entity.TransactionStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccountMapperTest {

    @Test
    void should_map_dto_to_account() {
        var accountExternalId = UUID.randomUUID();

        var latestTrxDto = new TransactionDto();
        latestTrxDto.setAmount(BigDecimal.TEN);
        latestTrxDto.setCurrency("GBP");
        latestTrxDto.setDirection(TransactionDirection.CREDIT);
        latestTrxDto.setTransactionStatus(TransactionStatus.COMPLETED);
        latestTrxDto.setExternalId(UUID.randomUUID().toString());
        latestTrxDto.setAccountExternalId(accountExternalId.toString());

        var accountDto = new AccountDto();
        accountDto.setExternalId(accountExternalId.toString());
        accountDto.setBalance(BigDecimal.TEN);
        accountDto.setName("howard hawks");
        accountDto.setLatestTransaction(latestTrxDto);

        Account account = AccountMapper.INSTANCE.toEntity(accountDto);

        //results
        assertThat(account).isNotNull();
        assertThat(account.getName()).isEqualTo("howard hawks");
        assertThat(account.getBalance()).isEqualTo(BigDecimal.TEN);
        assertThat(account.getLatestTransaction()).isNotNull();
        assertThat(account.getLatestTransaction().getCurrency()).isEqualTo("GBP");
        assertThat(account.getLatestTransaction().getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(account.getLatestTransaction().getTransactionStatus()).isEqualTo(TransactionStatus.COMPLETED);
        assertThat(account.getLatestTransaction().getDirection()).isEqualTo(TransactionDirection.CREDIT);
        assertThat(account.getLatestTransaction().getAccountExternalId()).isEqualTo(account.getExternalId());
    }

    @Test
    void should_map_account_to_dto() {
        var accountExternalId = UUID.randomUUID();

        var latestTrx = new Transaction();
        latestTrx.setId(1L);
        latestTrx.setVersion(0L);
        latestTrx.setCreatedAt(LocalDateTime.now());
        latestTrx.setUpdatedAt(LocalDateTime.now());
        latestTrx.setAmount(BigDecimal.TEN);
        latestTrx.setCurrency("GBP");
        latestTrx.setDirection(TransactionDirection.CREDIT);
        latestTrx.setTransactionStatus(TransactionStatus.COMPLETED);
        latestTrx.setExternalId(UUID.randomUUID());
        latestTrx.setAccountExternalId(accountExternalId);

        var account = new Account();
        account.setId(1L);
        account.setVersion(0L);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setExternalId(accountExternalId);
        account.setBalance(BigDecimal.TEN);
        account.setName("howard hawks");
        account.setLatestTransaction(latestTrx);

        AccountDto accountDto = AccountMapper.INSTANCE.fromEntity(account);

        //results
        assertThat(accountDto).isNotNull();
        assertThat(accountDto.getName()).isEqualTo("howard hawks");
        assertThat(accountDto.getBalance()).isEqualTo(BigDecimal.TEN);
        assertThat(accountDto.getLatestTransaction()).isNotNull();
        assertThat(accountDto.getLatestTransaction().getCurrency()).isEqualTo("GBP");
        assertThat(accountDto.getLatestTransaction().getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(accountDto.getLatestTransaction().getTransactionStatus()).isEqualTo(TransactionStatus.COMPLETED);
        assertThat(accountDto.getLatestTransaction().getDirection()).isEqualTo(TransactionDirection.CREDIT);
        assertThat(accountDto.getLatestTransaction().getAccountExternalId()).isEqualTo(accountExternalId.toString());
    }
}