package com.test.paysend.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

@NoArgsConstructor

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JacksonXmlRootElement(localName = "response")
public class ApiErrorResponse extends ApiResponse {

    @Builder
    public ApiErrorResponse(int resultCode) {
        this.resultCode = resultCode;
    }
}
