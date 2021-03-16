package com.test.paysend.service.impl;

import com.test.paysend.dto.UserDTO;
import com.test.paysend.exception.BusinessException;
import com.test.paysend.service.RequestValidator;
import com.test.paysend.util.RequestTypes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Validates user request data
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRequestValidatorImpl implements RequestValidator<UserDTO> {

    private final Validator validator;

    @Override
    public void validateRequest(UserDTO request) {
        log.info("Validating {} user request data", request);
        request.setPassword(request.getPassword());
        request.setPassword(request.getUserName());
        if(RequestTypes.findByValue(request.getRequestType()) == null) {
            log.warn("Request type is invalid({})", request.getRequestType());
            throw new BusinessException("Invalid request type");
        }
        Set<ConstraintViolation<UserDTO>> constraintViolations = validator.validate(UserDTO.builder().password(
                request.getPassword()).userName(request.getUserName()).build());
        if (!constraintViolations.isEmpty()) {
            StringBuilder fieldErrors = new StringBuilder();
            for (ConstraintViolation<UserDTO> constraintViolation : constraintViolations) {
                fieldErrors.append(constraintViolation.getPropertyPath().toString()).append(" property - ").
                        append(constraintViolation.getMessage()).append("(").append(constraintViolation.getInvalidValue()).append(")\n");
            }
            log.error("The following fields are incorrect in user request\n {}", fieldErrors.toString());
            throw new BusinessException("The order registration message contains invalid fields : \n");
        }
    }
}
