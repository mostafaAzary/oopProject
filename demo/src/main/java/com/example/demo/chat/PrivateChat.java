package com.example.demo.chat;

public class PrivateChat {
    String accountID1;
    String accountID2;
    String ID;

    public String getID() {
        return ID;
    }

    public String getAccountID1() {
        return accountID1;
    }

    public String getAccountID2() {
        return accountID2;
    }

    public PrivateChat(String accountID1, String accountID2) {
        this.accountID1 = accountID1;
        this.accountID2 = accountID2;
        this.ID = accountID1 + "," + accountID2;
    }

    public PrivateChat(String accountID1, String accountID2, String ID) {
        this.accountID1 = accountID1;
        this.accountID2 = accountID2;
        this.ID = ID;
    }
}
