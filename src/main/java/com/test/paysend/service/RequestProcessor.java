package com.test.paysend.service;

import com.test.paysend.dto.ApiResponse;
import com.test.paysend.dto.RequestGeneric;

/**
 * Class intended to define request receive and response generation functionality
 *
 * @param <T> - request type
 * @param <K> - response type
 */
public interface RequestProcessor<T extends RequestGeneric, K extends ApiResponse> {
    K processRequestWithResponse(T request);
}
