package ea.slartibartfast.demospringbootjpa.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "accounts")
@Table(name = "accounts")
@SQLRestriction("deleted_at is null")
@NamedEntityGraph(name = "Account.latestTransaction",
        attributeNodes = @NamedAttributeNode("latestTransaction")
)
public class Account extends AuditBaseEntity {

    private String name;

    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinFormula("(" +
            "SELECT trx.id " +
            "FROM transactions trx " +
            "WHERE trx.account_external_id = external_id " +
            "ORDER BY trx.created_at DESC " +
            "LIMIT 1" +
            ")")
    @JsonBackReference
    private Transaction latestTransaction;
}
