package ea.slartibartfast.demospringbootjpa.model.dto;

import ea.slartibartfast.demospringbootjpa.model.entity.TransactionDirection;
import ea.slartibartfast.demospringbootjpa.model.entity.TransactionStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDto {

    private String externalId;
    private TransactionDirection direction;
    private BigDecimal amount;
    private String currency;
    private TransactionStatus transactionStatus;
    private String accountExternalId;
}
