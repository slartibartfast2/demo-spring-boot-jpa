package ea.slartibartfast.demospringbootjpa.repository;

import ea.slartibartfast.demospringbootjpa.model.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends BaseNaturalIdRepository<Transaction, UUID> {

    Optional<Transaction> findTransactionByExternalId(UUID externalId);

    List<Transaction> findAllByAccountExternalId(UUID accountExternalId);
}
