package ea.slartibartfast.demospringbootjpa.api.request;

import ea.slartibartfast.demospringbootjpa.model.entity.TransactionStatus;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTransactionStatusRequest {

    private String transactionId;
    private TransactionStatus newStatus;
}
