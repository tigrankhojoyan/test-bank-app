package com.test.paysend.dao.impl;

import com.test.paysend.dao.UserDAO;
import com.test.paysend.exception.InvalidUserException;
import com.test.paysend.model.User;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

/**
 * Class intended to perform DB operations for {@link User} class
 */
@AllArgsConstructor
@Repository
public class UserDAOJDBCImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * non-Javadoc
     *
     * @see UserDAO#save(User)
     */
    @Override
    public Long save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(UserDAO.INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    /**
     * non-Javadoc
     *
     * @see UserDAO#getByUsername(String)
     */
    @Override
    public User getByUsername(String userName) {
        try {
            return jdbcTemplate.queryForObject(UserDAO.GET_USER_BY_USERNAME_QUERY,
                    (rs, rowNum) ->
                            User.builder().id(rs.getLong("id"))
                                    .userName(rs.getString("userName"))
                                    .password(rs.getString("password")).build()
                    ,
                    userName);
        } catch (EmptyResultDataAccessException ex) {
            throw new InvalidUserException("User with provided username does not exist in database(" + userName + ")");
        }

    }

    /**
     * non-Javadoc
     *
     * @see UserDAO#checkIfUserExistsByUserName(String)
     */
    @Override
    public boolean checkIfUserExistsByUserName(String username) {
        Integer count = jdbcTemplate.queryForObject(UserDAO.CHECK_IF_USER_EXISTS_QUERY, Integer.class, username);
        return count != null && count == 1;
    }


}
