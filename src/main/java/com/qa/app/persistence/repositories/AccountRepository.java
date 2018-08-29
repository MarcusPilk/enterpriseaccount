package com.qa.app.persistence.repositories;

import com.qa.app.persistence.domain.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> getAllAccounts();

    String createAccount(String account);

    String updateAccount(int id, String account);

    String deleteAccount(int id);
}
