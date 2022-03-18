package com.eteration.simplebanking.controller;

import java.util.List;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


// This class is a place holder you can change the complete implementation
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account/{string}")
    // or
    // @GetMapping("/accounts")
    // public ResponseEntity<Account> getAccount(@RequestParam("string") String string)
    // localhost:8080/accounts/{string}
    public ResponseEntity<Account> getAccount(@PathVariable(value = "string") String string) {
        Account account = accountService.findAccount(string);
        if(account == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/account")
    public void saveAccount(@RequestBody Account acc) {
        accountService.saveAccount(acc);
    }

    @GetMapping("/account")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("/account/{string}/credit/")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String string, @RequestBody DepositTransaction depositTransaction) {
        this.getAccount(string).getBody().post(depositTransaction);
        
        return ResponseEntity.ok().body(new TransactionStatus(depositTransaction));
    }

    @PostMapping("/account/{string}/debit/")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String string, @RequestBody WithdrawalTransaction withdrawalTransaction) {
        this.getAccount(string).getBody().post(withdrawalTransaction);
        return ResponseEntity.ok().body(new TransactionStatus(withdrawalTransaction));
    }
}