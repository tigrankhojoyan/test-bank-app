package com.test.paysend.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
public class User implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private List<Account> accounts;
}
