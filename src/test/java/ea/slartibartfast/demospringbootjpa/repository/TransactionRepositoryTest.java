package ea.slartibartfast.demospringbootjpa.repository;

import ea.slartibartfast.demospringbootjpa.config.JpaConfig;
import ea.slartibartfast.demospringbootjpa.model.entity.Transaction;
import ea.slartibartfast.demospringbootjpa.model.entity.TransactionDirection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@Import(JpaConfig.class)
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void should_retrieve_all_transactions_by_account() {
        var acc2ExternalId = UUID.fromString("5dc7fe93-bf11-480a-a33e-54eaf49e88cb");

        var transactions = transactionRepository.findAllByAccountExternalId(acc2ExternalId);
        System.out.println("Transactions retrieved");
        assertThat(transactions).hasSize(2)
                                .extracting(Transaction::getExternalId, Transaction::getDirection, Transaction::getAmount)
                                .containsExactlyInAnyOrder(tuple(UUID.fromString("3d1c4db8-6ef4-431f-8006-17599583d427"), TransactionDirection.CREDIT, BigDecimal.valueOf(100).setScale(2)),
                                                           tuple(UUID.fromString("4e1c4db8-6ef4-431f-8006-17599583d427"), TransactionDirection.DEBIT, BigDecimal.valueOf(25).setScale(2)));
    }
}