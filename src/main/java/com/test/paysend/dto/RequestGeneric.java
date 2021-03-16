package com.test.paysend.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class RequestGeneric {
    @JacksonXmlProperty(localName = "request-type")
    private String requestType;
}
