package com.test.paysend.service;

import com.test.paysend.dto.UserDTO;

/**
 * Service intended to perform operations conserning user
 */
public interface UserService {

    /**
     * Performs registration action of given user
     *
     * @param user
     */
    void registerUser(UserDTO user);

    /**
     * Retrieves user data by given credentials
     *
     * @param userName
     * @return
     */
    UserDTO getUserByUserNameAndPassword(String userName, String password);


    /**
     * Check if user by given username exists in database
     *
     * @param userName
     * @return
     */
    boolean doesUserExist(String userName);

}
