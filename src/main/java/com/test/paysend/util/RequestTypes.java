package com.test.paysend.util;

/**
 * Request types
 */
public enum RequestTypes {
    CREATE_AGT("CREATE-AGT"),
    GET_BALANCE("GET-BALANCE");

    private final String requestValue;

    RequestTypes(String requestValue) {
        this.requestValue = requestValue;
    }

    public String getRequestValue() {
        return requestValue;
    }

    public static RequestTypes findByValue(String requestValue) {
        for (RequestTypes requestType:values()) {
            if(requestType.requestValue.equalsIgnoreCase(requestValue)) {
                return requestType;
            }
        }
        return null;
    }
}
