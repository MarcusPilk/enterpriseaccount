package com.qa.app.service.repositories;

import com.google.gson.Gson;
import com.qa.app.domain.Account;
import com.qa.app.service.business.AccountRepository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
@Default
public class AccountDB implements AccountRepository {

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    private Gson gson;

    public AccountDB() {
        gson = new Gson();
    }

    @Override
    public String getAllAccounts() {
        Query query = manager.createQuery("SELECT a FROM Account a",Account.class);
        return gson.toJson(query.getResultList());
    }

    @Override @Transactional(REQUIRED)
    public String createAccount(String account){
        Account aAccount = gson.fromJson(account,Account.class);
        manager.persist(aAccount);
        return "{\"message\": \"Account is created\"}";
    }

    @Override @Transactional(REQUIRED)
    public String updateAccount(int id, String account) {
        Account updatedAccount = gson.fromJson(account,Account.class);
        Account accountFromDB = manager.find(Account.class,id);
        if (accountFromDB != null) {
            accountFromDB = updatedAccount;
            manager.merge(accountFromDB);
        }
        return "{\"message\": \"Account is updated\"}";
    }

    @Override     @Transactional(REQUIRED)
    public String deleteAccount(int id) {
        Account aAccount = manager.find(Account.class,id);
        manager.remove(aAccount);
        return "{\"message\": \"Account is removed\"}";
    }


}
