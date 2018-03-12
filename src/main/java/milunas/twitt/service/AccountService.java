package milunas.twitt.service;

import milunas.twitt.command.AccountCreate;
import milunas.twitt.exception.PasswordDoesNotMatchException;
import milunas.twitt.exception.UsernameExistsException;
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
    private SecurityService security;
    @Autowired
    private AccountRepository repository;
    private static final String BASIC_URL = "localhost:8080/api/";

    public ResponseEntity<Void> createNewAccount(AccountCreate accountCreate) {

        String username = accountCreate.getUsername();
        if(isUsernameExist(username)) {
            throw new UsernameExistsException(username);
        }
        String password = security.passwordEncoder().encode(accountCreate.getPassword());
        String email = accountCreate.getEmail();
        Account account = new Account(username, password, email);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Localization", BASIC_URL + account.getUsername());

        repository.save(account);
        return new ResponseEntity<>(responseHeaders,HttpStatus.CREATED);
    }

    public ResponseEntity<Void> generateToken(AccountCreate knockerAccount){

         String knockerUsername = knockerAccount.getUsername();
         String knockerPassword = security
                 .passwordEncoder()
                 .encode(knockerAccount.getPassword());

         Account databaseAccount = repository.findByUsername(knockerUsername);

         if(isUserNotExist(databaseAccount)){
             throw new UsernameNotFoundException("No account found with username:" + knockerUsername);
         }

         if(isPasswordNotMatchUser(knockerPassword, databaseAccount.getPassword())){
             throw new PasswordDoesNotMatchException();
         }

         String token = security.generateToken(knockerUsername);
         updateToken(databaseAccount, token);

         HttpHeaders responseHeaders = new HttpHeaders();
         responseHeaders.set("User", knockerUsername);
         responseHeaders.set("Bearer", token);

        return new ResponseEntity<>(responseHeaders, HttpStatus.ACCEPTED);
    }


    public boolean isUsernameExist(String username){
        return repository.findByUsername(username)!=null;
    }

    public boolean isUserNotExist(Account user){
        return user == null;
    }

    private boolean isPasswordNotMatchUser(String givenPassword, String userPassword){
        return !givenPassword.equals(userPassword);
    }

    private void updateToken(Account account, String token){
        account.setUserSecurityToken(token);
        repository.save(account);
    }


}
