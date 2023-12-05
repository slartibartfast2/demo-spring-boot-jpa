package ea.slartibartfast.demospringbootjpa.repository;

import ea.slartibartfast.demospringbootjpa.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void should_retrieve_account_with_latest_transaction() {
        var acc2ExternalId = UUID.fromString("5dc7fe93-bf11-480a-a33e-54eaf49e88cb");
        var trxExternalId = UUID.fromString("4e1c4db8-6ef4-431f-8006-17599583d427");

        var account2 = accountRepository.findAccountWithLatestTransactionByExternalId(acc2ExternalId);
        System.out.println("Account-2 retrieved");
        assertThat(account2).isPresent();
        assertThat(account2.get().getExternalId()).isEqualTo(acc2ExternalId);
        assertThat(account2.get().getLatestTransaction()).isNotNull();
        var latestTrx = account2.get().getLatestTransaction();
        assertThat(latestTrx.getExternalId()).isEqualTo(trxExternalId);
        assertThat(latestTrx.getAccountExternalId()).isEqualTo(acc2ExternalId);
    }
}
