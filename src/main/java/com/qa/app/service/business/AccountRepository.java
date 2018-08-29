package com.qa.app.service.business;

public interface AccountRepository {
    String getAllAccounts();

    String createAccount(String account);

    String updateAccount(int id, String account);

    String deleteAccount(int id);
}
