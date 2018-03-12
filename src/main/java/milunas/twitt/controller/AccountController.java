package milunas.twitt.controller;

import milunas.twitt.command.AccountCreate;
import milunas.twitt.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/register")
    public ResponseEntity<Void> createNewAccount(@RequestBody AccountCreate accountCreate){
        return service.createNewAccount(accountCreate);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> getToken(@RequestBody AccountCreate account){
        return service.generateToken(account);
    }
}
