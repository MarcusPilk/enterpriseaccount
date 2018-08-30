package com.qa.app.service.business;

import com.google.gson.Gson;
import com.qa.app.domain.Account;

import javax.inject.Inject;

public class AccountService {

    @Inject
    private AccountRepository accountRepo;

    private Gson gson;

    public AccountService() {
        gson = new Gson();
    }

    public String checkNewUser(String accountToBeCreated){
        Account newAccount = gson.fromJson(accountToBeCreated,Account.class);
        if (newAccount.getAccountNumber() == 9999){
            return "You are blocked from creating a user with this Account Number";
        }else{
            accountRepo.createAccount(accountToBeCreated);
            return "";
        }
    }
}
