package ea.slartibartfast.demospringbootjpa.rest;

import ea.slartibartfast.demospringbootjpa.api.TransactionApi;
import ea.slartibartfast.demospringbootjpa.api.request.UpdateTransactionStatusRequest;
import ea.slartibartfast.demospringbootjpa.model.dto.TransactionDto;
import ea.slartibartfast.demospringbootjpa.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TransactionController implements TransactionApi {

    private final TransactionService transactionService;

    @Override
    public TransactionDto retrieveTransaction(String transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        return transactionService.createTransaction(transactionDto);
    }

    @Override
    public TransactionDto updateTransactionStatus(UpdateTransactionStatusRequest updateTransactionStatusRequest) {
        return transactionService.updateTransactionStatus(updateTransactionStatusRequest.getTransactionId(), updateTransactionStatusRequest.getNewStatus());
    }
}
