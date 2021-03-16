package com.test.paysend.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JacksonXmlRootElement(localName = "response")
public class RegistrationResponse extends ApiResponse {

    @Builder
    public RegistrationResponse(int resultCode) {
        this.resultCode = resultCode;
    }
}
