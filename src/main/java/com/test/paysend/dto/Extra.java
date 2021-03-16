package com.test.paysend.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "extra")
public class Extra {
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;
    @JacksonXmlText
    private String value;

    public Extra() {
    }

    public Extra(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
