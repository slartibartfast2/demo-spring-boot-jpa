package ea.slartibartfast.demospringbootjpa.repository;

import ea.slartibartfast.demospringbootjpa.model.entity.AuditBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseNaturalIdRepository<T extends AuditBaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {


    T getByExternalId(ID externalId);
}
