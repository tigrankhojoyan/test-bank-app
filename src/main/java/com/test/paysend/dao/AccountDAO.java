package com.test.paysend.dao;

import com.test.paysend.model.Account;

import java.util.List;


/**
 * Data access object methods definition for {@link Account} class
 */
public interface AccountDAO {
    String INSERT_ACCOUNT_QUERY = "INSERT INTO ACCOUNT (amount, bankName, user_id) values(?,?,?)";
    String FIND_ACCOUNTS_OF_USERNAME_QUERY = "SELECT * FROM ACCOUNT WHERE user_id=?";

    /**
     * Persists record into database
     *
     * @param account - persisting account
     * @return - true, if succeed
     */
    Boolean save(Account account);

    /**
     * Finds list of accounts for given user
     *
     * @param userId - id of user
     * @return - list of user's accounts
     */
    List<Account> findByUseId(Long userId);
}
