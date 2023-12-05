package ea.slartibartfast.demospringbootjpa.rest;

import ea.slartibartfast.demospringbootjpa.api.AccountApi;
import ea.slartibartfast.demospringbootjpa.model.dto.AccountDto;
import ea.slartibartfast.demospringbootjpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    @Override
    public AccountDto retrieveAccount(String accountId) {
        return accountService.getAccount(accountId);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        return accountService.updateAccount(accountDto);
    }

    @Override
    public AccountDto deleteAccount(String accountId) {
        return accountService.deleteAccount(accountId);
    }
}
