package com.qa.app.service.repositories;

import com.google.gson.Gson;
import com.qa.app.domain.Account;
import com.qa.app.service.business.AccountRepository;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Alternative
public class AccountMap implements AccountRepository {

    private Map<Integer,Account> accountMap;
    private Gson gson;

    public AccountMap() {
        this.accountMap = new HashMap<>() ;
        gson = new Gson();
    }


    @Override
    public String getAllAccounts() {
        return gson.toJson(accountMap.values());
    }

    @Override
    public String createAccount(String accountToCreate) {
        Account aAccount = gson.fromJson(accountToCreate,Account.class);
        accountMap.put(aAccount.getId(),aAccount);
        return "Account created successfully";
    }

    @Override
    public String updateAccount(int id, String accountToUpdate) {
        Account updatedAccount = gson.fromJson(accountToUpdate,Account.class);
        accountMap.replace(id,updatedAccount);
        return "Account updated successfully";    }

    @Override
    public String deleteAccount(int id) {
        accountMap.remove(id);
        return "Account removed successfully";
    }
}
