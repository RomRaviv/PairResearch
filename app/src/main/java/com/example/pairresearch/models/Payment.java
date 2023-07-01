package com.example.pairresearch.models;

public class Payment {
    public int amount;
    public String type_;

    public Payment(){ }
    public Payment(int amount, String type_) {
        this.amount = amount;
        this.type_ = type_;
    }
    

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType_() {
        return type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    
}
