package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class WithdrawalTransaction extends Transaction {
    
    public WithdrawalTransaction(double amount) {
        super(-amount);
        setType("WithdrawalTransaction");
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;  
    }
}


