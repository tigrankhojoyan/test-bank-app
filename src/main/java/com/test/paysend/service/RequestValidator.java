package com.test.paysend.service;

import com.test.paysend.dto.RequestGeneric;

/**
 * Class intended to perform validation for requests
 * @param <T>
 */
public interface RequestValidator<T extends RequestGeneric> {

    /**
     * Validates request
     * @param request - sent request
     */
    void validateRequest(T request);
}
