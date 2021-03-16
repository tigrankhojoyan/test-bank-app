package com.test.paysend.service.impl;

import com.test.paysend.dto.RegistrationResponse;
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
public class RegistrationRequestProcessor implements RequestProcessor<UserDTO, RegistrationResponse> {

    private final UserService userService;
    private final RequestValidator<UserDTO> userDTORequestValidator;

    /**
     * Processes user registration request
     */
    @Override
    public RegistrationResponse processRequestWithResponse(UserDTO request) {
        log.info("Validating user registration request");
        userDTORequestValidator.validateRequest(request);
        log.info("Processing user registration request");
        userService.registerUser(request);
        return RegistrationResponse.builder().resultCode(0).build();
    }

}
