package com.test.paysend.dao;

import com.test.paysend.model.User;

/**
 * Data access object methods definition for {@link User} class
 */
public interface UserDAO {
    String INSERT_USER_QUERY = "INSERT INTO USER (username, password) values(?,?)";
    String GET_USER_BY_USERNAME_QUERY = "SELECT * FROM USER WHERE username=?";
    String CHECK_IF_USER_EXISTS_QUERY = "SELECT COUNT(1) FROM USER WHERE username=?";

    /**
     * Persists record into database
     *
     * @param user - persisting user
     * @return - generated id of record
     */
    Long save(User user);

    /**
     * Retrieves user record from db by given username
     *
     * @param username - value of username
     * @return - retrieved object
     */
    User getByUsername(String username);

    /**
     * Checks if user by given username exists in db
     *
     * @param username - value of username
     * @return - true, if exists, false otherwise
     */
    boolean checkIfUserExistsByUserName(String username);
}
