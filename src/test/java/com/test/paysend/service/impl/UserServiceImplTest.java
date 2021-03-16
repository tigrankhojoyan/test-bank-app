package com.test.paysend.service.impl;

import com.test.paysend.dao.AccountDAO;
import com.test.paysend.dao.UserDAO;
import com.test.paysend.dto.UserDTO;
import com.test.paysend.exception.DuplicateRecordDAOException;
import com.test.paysend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceImplTest {

    @MockBean
    UserDAO userDAO;

    @MockBean
    AccountDAO accountDAO;

    @Autowired
    UserService userService;

    @Test
    public void registerUserTest() {
        Mockito.when(userDAO.save(Mockito.any())).then(invocationOnMock -> {
            System.out.println("in mock 'userDAO.save'");
            return (long) 1;
        });
        Mockito.when(accountDAO.save(Mockito.any())).then(invocationOnMock -> {
            System.out.println("in mock 'accountDAO.save'");
            return true;
        });
        UserDTO user = UserDTO.builder().userName("testUs").password("passw3344").build();
        userService.registerUser(user);
    }

    @Test
    public void registerUserDuplicateTest() {
        Mockito.when(userDAO.checkIfUserExistsByUserName(Mockito.any())).then(invocationOnMock -> {
            System.out.println("in mock 'userDao.checkIfUserExistsByUserName'");
            return true;
        });
        UserDTO user = UserDTO.builder().userName("testUs").password("passw3344").build();
        assertThrows(
                DuplicateRecordDAOException.class, () -> userService.registerUser(user)
        );
    }

}