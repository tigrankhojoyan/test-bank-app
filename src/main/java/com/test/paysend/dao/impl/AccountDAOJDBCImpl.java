package com.test.paysend.dao.impl;

import com.test.paysend.dao.AccountDAO;
import com.test.paysend.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class intended to perform DB operations for {@link Account} class
 */
@AllArgsConstructor
@Repository
public class AccountDAOJDBCImpl implements AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * non-Javadoc
     *
     * @see AccountDAO#save(Account)
     */
    @Override
    public Boolean save(Account account) {
        return jdbcTemplate.execute(AccountDAO.INSERT_ACCOUNT_QUERY, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setBigDecimal(1, account.getAmount());
            ps.setString(2, account.getBankName());
            ps.setLong(3, account.getUser_id());
            return ps.execute();
        });
    }

    /**
     * non-Javadoc
     *
     * @see AccountDAO#findByUseId(Long)
     */
    @Override
    public List<Account> findByUseId(Long userId) {
        return jdbcTemplate.query(AccountDAO.FIND_ACCOUNTS_OF_USERNAME_QUERY,
                (rs, rowNum) -> Account.builder().id(rs.getLong("id"))
                        .amount(rs.getBigDecimal("amount"))
                        .bankName(rs.getString("bankName")).build(), userId);
    }
}
