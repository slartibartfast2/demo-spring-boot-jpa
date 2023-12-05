package ea.slartibartfast.demospringbootjpa.repository;

import ea.slartibartfast.demospringbootjpa.model.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends BaseNaturalIdRepository<Account, UUID> {

    Optional<Account> findAccountByExternalId(UUID externalId);

    @EntityGraph(value = "Account.latestTransaction")
    Optional<Account> findAccountWithLatestTransactionByExternalId(UUID externalId);
}
