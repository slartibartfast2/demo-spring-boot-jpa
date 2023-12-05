package ea.slartibartfast.demospringbootjpa.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity(name = "transactions")
@Audited
@AuditOverrides({
        @AuditOverride(forClass = AuditBaseEntity.class),
        @AuditOverride(forClass = BaseEntity.class)
})
@Table(name = "transactions")
@SQLRestriction("deleted_at is null")
public class Transaction extends AuditBaseEntity {

    @Column(updatable = false)
    private TransactionDirection direction;

    @Column(updatable = false)
    private BigDecimal amount;

    @Column(updatable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus transactionStatus;

    @Column(name = "account_external_id")
    private UUID accountExternalId;

    /*
     * @ManyToOne(fetch = FetchType.LAZY) doesn't work on non-primary key referenced column
     *
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_external_id", referencedColumnName = "external_id")
    @JsonManagedReference
    private Account account;
    *
    */
}
