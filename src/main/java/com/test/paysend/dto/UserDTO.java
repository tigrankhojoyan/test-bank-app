package com.test.paysend.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.test.paysend.util.XmlUtils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "request")
public class UserDTO extends RequestGeneric {
    private Long id;

    private static final String PASSWORD_STRING = "password";
    private static final String LOGIN_STRING = "login";

    @JacksonXmlProperty(localName = "request-type")
    private String requestType;

    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Extra> extra = new ArrayList<>();

    @NotNull
    @Size(min = 3, max = 30, message = "The username length must be between 5...30 characters")
    private String userName;

    @NotNull
    @Size(min = 3, max = 30, message = "The password length must be between 5...30 characters")
    private String password;

    private List<AccountDTO> accounts;

    @Builder
    public UserDTO(String requestType, Long id, List<Extra> extra, String userName, String password) {
        super(requestType);
        this.id = id;
        this.extra = extra;
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        if (password == null && !extra.isEmpty()) {
            return XmlUtils.findAttributeValueByProperty(PASSWORD_STRING, extra);
        }
        return password;
    }

    public String getUserName() {
        if (userName == null && !extra.isEmpty()) {
            return XmlUtils.findAttributeValueByProperty(LOGIN_STRING, extra);
        }
        return userName;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", requestType='" + requestType + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + "************" + '\'' +
                '}';
    }
}
