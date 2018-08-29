package com.qa.app.persistence.repositories;

import com.google.gson.Gson;
import com.qa.app.persistence.domain.Account;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class AccountDB implements AccountRepository{

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    @Inject
    private Gson gson;

    @Override
    public List<Account> getAllAccounts() {
        Query query = manager.createQuery("SELECT a FROM Account a",Account.class);
        return query.getResultList();
    }

    @Override @Transactional(REQUIRED)
    public String createAccount(String account){
        Account aAccount = gson.fromJson(account,Account.class);
        manager.persist(aAccount);
        return "Account created successfully";
    }

    @Override @Transactional(REQUIRED)
    public String updateAccount(int id, String account) {
        Account updatedAccount = gson.fromJson(account,Account.class);
        Account accountFromDB = manager.find(Account.class,id);
        if (accountFromDB != null) {
            accountFromDB = updatedAccount;
            manager.merge(accountFromDB);
        }
        return "Account updated successfully";
    }

    @Override     @Transactional(REQUIRED)
    public String deleteAccount(int id) {
        Account aAccount = manager.find(Account.class,id);
        manager.remove(aAccount);
        return "Account deleted successfully";
    }


}
