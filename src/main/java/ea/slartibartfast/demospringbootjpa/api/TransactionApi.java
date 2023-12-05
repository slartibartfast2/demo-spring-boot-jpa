package ea.slartibartfast.demospringbootjpa.api;

import ea.slartibartfast.demospringbootjpa.api.request.UpdateTransactionStatusRequest;
import ea.slartibartfast.demospringbootjpa.model.dto.TransactionDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
public interface TransactionApi {

    @GetMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    TransactionDto retrieveTransaction(@RequestParam String transactionId);

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TransactionDto createTransaction(@RequestBody TransactionDto transactionDto);

    @PatchMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TransactionDto updateTransactionStatus(@RequestBody UpdateTransactionStatusRequest updateTransactionStatusRequest);
}
