package com.test.paysend.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@JacksonXmlRootElement(localName = "request")
public class AccountDTO extends RequestGeneric {
    private Long id;
    private BigDecimal amount;
    //may be enum...
    private String bankName;

    @Builder
    public AccountDTO(String requestType, Long id, BigDecimal amount, String bankName) {
        super(requestType);
        this.id = id;
        this.amount = amount;
        this.bankName = bankName;
    }
}
