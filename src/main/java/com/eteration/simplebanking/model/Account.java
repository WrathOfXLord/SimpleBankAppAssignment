package com.eteration.simplebanking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

// This class is a place holder you can change the complete implementation

@Entity(name = "Account")
public class Account {
    private String accountNumber;
    private String owner;
    private double balance;
	
	// @OneToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "transactionId")
    private List<Transaction> transactions;

    public Account() {
        accountNumber = "";
        owner = "";
        balance = 0.0;
        transactions = new ArrayList<Transaction>();
    }
    
    public Account(String owner, String accountNumber) {
    	this.accountNumber = accountNumber;
    	this.owner = owner;
        balance = 0.0;
        transactions = new ArrayList<Transaction>();
    }

    // run time polymorphic function accepts all types of transactions
    public void post(Transaction t) {
        balance += t.getAmount();
        t.setAmount(Math.abs(t.getAmount()));
        transactions.add(t);
    }

    @Id
    @Column(name = "accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String num) {
        accountNumber = num;
    }

    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name = "balance")
    public double getBalance() { 
        return balance; 
    }

    public void setBalance(double value) {
        balance = value;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if(balance < amount)
            throw new InsufficientBalanceException();
        balance -= amount;
    }
    
    @OneToMany
    @JoinTable(
        name = "Transaction",
        joinColumns = @JoinColumn(name = "accountNumber"),
        inverseJoinColumns = @JoinColumn(name = "transactionId")
    )
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
