package com.example.demo.account;


public class CommercialAccount extends Account {
    int views;

    public CommercialAccount(String ID, String password) {
        super(ID, password);
        this.views = 0;
    }
}
