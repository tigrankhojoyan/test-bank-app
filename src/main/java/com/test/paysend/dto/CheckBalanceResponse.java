package com.test.paysend.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@JacksonXmlRootElement(localName = "response")
public class CheckBalanceResponse extends ApiResponse {
    @JacksonXmlProperty(localName = "extra")
    private Extra balance;

    @Builder
    public CheckBalanceResponse(int resultCode, BigDecimal balance) {
        this.resultCode = resultCode;
        this.balance = new Extra("balance", balance.toString());
    }

}