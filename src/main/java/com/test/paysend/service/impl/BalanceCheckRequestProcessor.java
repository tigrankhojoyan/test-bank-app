package com.test.paysend.service.impl;

import com.test.paysend.dto.CheckBalanceResponse;
import com.test.paysend.dto.UserDTO;
import com.test.paysend.service.RequestProcessor;
import com.test.paysend.service.RequestValidator;
import com.test.paysend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Slf4j
@Service
public class BalanceCheckRequestProcessor implements RequestProcessor<UserDTO, CheckBalanceResponse> {

    private final UserService userService;
    private final RequestValidator<UserDTO> userDTORequestValidator;

    /**
     * Processes check balance request
     */
    @Override
    public CheckBalanceResponse processRequestWithResponse(UserDTO request) {
        log.info("Validating user balance check request");
        userDTORequestValidator.validateRequest(request);
        log.info("Processing user balance check request");
        UserDTO loginUserData = userService.getUserByUserNameAndPassword(request.getUserName(), request.getPassword());
        return CheckBalanceResponse.builder().resultCode(0)
                //static value, can be logic according to selected bank
                .balance(loginUserData.getAccounts().get(0).getAmount())
                .build();
    }
}
