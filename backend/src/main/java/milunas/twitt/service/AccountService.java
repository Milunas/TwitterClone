package milunas.twitt.service;

import milunas.twitt.command.AccountCreate;
import milunas.twitt.exception.PasswordDoesNotMatchException;
import milunas.twitt.exception.UsernameAlreadyExistsException;
import milunas.twitt.exception.UsernameNotFoundException;
import milunas.twitt.model.Account;
import milunas.twitt.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;
    private static final String BASIC_URL = "localhost:8080/api/user";

    public ResponseEntity<Void> createNewAccount(AccountCreate accountCreate) {

        String username = accountCreate.getUsername();

        if(isUsernameExist(username)) {
            throw new UsernameAlreadyExistsException(username);
        }

        Account account = parseFromCommand(accountCreate);
        repository.save(account);

        return new ResponseEntity<>(location(account),HttpStatus.CREATED);
    }

    private boolean isUsernameExist(String username){
        return repository.findByUsername(username)!=null;
    }

    private Account parseFromCommand(AccountCreate accountCreate){
        Account account = new Account();
        account.setUsername(accountCreate.getUsername());
        account.setPassword(accountCreate.getPassword());
        account.setEmail(accountCreate.getEmail());
        return account;
    }

    public ResponseEntity<Void> login(AccountCreate clientAccount) {

        String clientUsername = clientAccount.getUsername();
        Account dbAccount = repository.findByUsername(clientUsername);

        if (dbAccount == null){
            throw new UsernameNotFoundException(clientUsername);
        }
        if (clientAccount.getPassword()!=dbAccount.getPassword()){
            throw new PasswordDoesNotMatchException();
        }

        return new ResponseEntity(location(dbAccount),HttpStatus.ACCEPTED);
    }

    private HttpHeaders location(Account account){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location", BASIC_URL + account.getId());
        return responseHeaders;
    }

}
