package com.test.paysend.controller;

import com.test.paysend.dto.CheckBalanceResponse;
import com.test.paysend.dto.RegistrationResponse;
import com.test.paysend.dto.UserDTO;
import com.test.paysend.service.RequestProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Controller intended to respond requests concerning 'user'
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/user",
        produces = MediaType.APPLICATION_XML_VALUE,
        consumes = MediaType.APPLICATION_XML_VALUE
)
@Slf4j
public class UserDataController {

    private final RequestProcessor<UserDTO, RegistrationResponse> userRegistrationProcessor;
    private final RequestProcessor<UserDTO, CheckBalanceResponse> balanceCheckProcessor;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public RegistrationResponse registerUser(@RequestBody UserDTO userDTO) {
        log.info("Processing registration request");
        return userRegistrationProcessor.processRequestWithResponse(userDTO);
    }

    @PostMapping("/getbalance")
    @ResponseStatus(value = HttpStatus.OK)
    public CheckBalanceResponse checkBalance(@RequestBody UserDTO userDTO) {
        log.info("Processing balance check request");
        return balanceCheckProcessor.processRequestWithResponse(userDTO);
    }


}
