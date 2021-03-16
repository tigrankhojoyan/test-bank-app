package com.test.paysend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    private Long id;
    private BigDecimal amount;
    //may be enum...
    private String bankName;
    //Foreign key
    private Long user_id;


}
