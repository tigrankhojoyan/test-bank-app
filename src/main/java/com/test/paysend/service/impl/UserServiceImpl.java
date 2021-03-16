package com.test.paysend.service.impl;

import com.test.paysend.dao.AccountDAO;
import com.test.paysend.dao.UserDAO;
import com.test.paysend.dto.UserDTO;
import com.test.paysend.exception.DuplicateRecordDAOException;
import com.test.paysend.exception.InvalidCredentialsException;
import com.test.paysend.exception.InvalidUserException;
import com.test.paysend.model.Account;
import com.test.paysend.model.User;
import com.test.paysend.service.UserService;
import com.test.paysend.service.mapper.DataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Non javadoc
 *
 * @see UserService
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final DataMapper<UserDTO, User> userDataMapper;
    private final UserDAO userDAO;
    private final AccountDAO accountDAO;
    private final PasswordEncoder encoder;

    /**
     * Non javadoc
     *
     * @see UserService#registerUser(UserDTO)
     */
    @Override
    @Transactional//(isolation = Isolation.SERIALIZABLE)
    public void registerUser(UserDTO user) {
//        if (userDAO.checkIfUserExistsByUserName(user.getUserName())) {
//            throw new DuplicateRecordDAOException("User by provided username already exists(" + user.getUserName() + ")");
//        }
        User userDb = userDataMapper.convertDtoToModel(user);
        userDb.setPassword(encoder.encode(user.getPassword()));
        Long userId = userDAO.save(userDb);
        accountDAO.save(Account.builder().amount(new BigDecimal("0.0")).bankName("SBERBANK").user_id(userId).build());
    }

    /**
     * Non javadoc
     *
     * @see UserService#getUserByUserNameAndPassword(String, String)
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByUserNameAndPassword(String userName, String password) {
        User persistedUser = userDAO.getByUsername(userName);
        if (!encoder.matches(password, persistedUser.getPassword())) {
            throw new InvalidCredentialsException("The password is incorrect");
        }
        List<Account> accounts = accountDAO.findByUseId(persistedUser.getId());
        persistedUser.setAccounts(accounts);
        return userDataMapper.convertModelToDto(persistedUser);
    }

    /**
     * Non javadoc
     *
     * @see UserService#doesUserExist(String)
     */
    @Override
    public boolean doesUserExist(String userName) {
        return false;
    }


}
