package com.eteration.simplebanking.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// This class is a place holder you can change the complete implementation
@Entity(name = "Transaction")
public abstract class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
	private Long transactionId;
	
	// @ManyToOne
	// @JoinColumn(name="transactionId")
    @Column(name = "amount")
    private double amount;
	
	// @ManyToOne
	// @JoinColumn(name="transactionId")
    @Column(name = "date")
    private LocalDateTime date;


    @Column(name = "type")
    protected String type;
        
    public Transaction(double amount) {
        this.amount = amount;
        date = LocalDateTime.now();
        type = "";
    }

    public Long getId() {
        return transactionId;
    }

    public void setId(Long id) {
        transactionId = id;
    }

    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public abstract String getType();
    public abstract void setType(String type);
}
