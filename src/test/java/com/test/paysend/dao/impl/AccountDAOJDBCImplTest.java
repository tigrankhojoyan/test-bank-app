package com.test.paysend.dao.impl;

import com.test.paysend.dao.UserDAO;
import com.test.paysend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AccountDAOJDBCImplTest {

    @Autowired
    private UserDAO userDao;

    @Test
    public void createUserTest() {
        User user = User.builder().userName("testUs").password("passw3344").build();
        userDao.save(user);
        assertTrue(userDao.checkIfUserExistsByUserName(user.getUserName()));
    }

    @Test
    public void createUserInvalidData() {
        User user = User.builder().userName("testUs").build();
        assertThrows(
                DataIntegrityViolationException.class, ()-> userDao.save(user)
        );

    }

}