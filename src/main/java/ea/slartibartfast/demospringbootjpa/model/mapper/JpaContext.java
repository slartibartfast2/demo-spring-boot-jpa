package ea.slartibartfast.demospringbootjpa.model.mapper;

import ea.slartibartfast.demospringbootjpa.model.entity.Account;
import ea.slartibartfast.demospringbootjpa.model.entity.Transaction;
import jakarta.persistence.EntityManager;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

public class JpaContext {

    private final EntityManager em;

    private Account accountEntity;

    public JpaContext(EntityManager em) {
        this.em = em;
    }

    @BeforeMapping
    public void setEntity(@MappingTarget Account account) {
        this.accountEntity = account;
    }

    @AfterMapping
    public void establishRelation(@MappingTarget Transaction transaction) {
        //transaction.setAccount(accountEntity);
    }

}