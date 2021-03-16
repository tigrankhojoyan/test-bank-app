package com.test.paysend.service.mapper.impl;

import com.test.paysend.dto.AccountDTO;
import com.test.paysend.model.Account;
import com.test.paysend.service.mapper.DataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AccountMapperImpl implements DataMapper<AccountDTO, Account> {

    private final ModelMapper accountDataMapper;

    @Override
    public Account convertDtoToModel(AccountDTO source) {
        log.info("Parsing the {} dto to db entity model(User)", source);
        return source != null ? accountDataMapper.map(source, Account.class) : null;
    }

    @Override
    public AccountDTO convertModelToDto(Account source) {
        log.info("Parsing the {} user db entity model to dto", source);
        return source != null ? accountDataMapper.map(source, AccountDTO.class) : null;
    }
}
