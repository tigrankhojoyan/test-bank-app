package com.test.paysend.service.mapper.impl;

import com.test.paysend.dto.UserDTO;
import com.test.paysend.model.User;
import com.test.paysend.service.mapper.DataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
@Slf4j
public class UserMapperImpl implements DataMapper<UserDTO, User> {

    private final ModelMapper accountDataMapper;

    @Override
    public User convertDtoToModel(UserDTO source) {
        log.info("Parsing the {} dto to db entity model(User)", source);
        return source != null ? accountDataMapper.map(source, User.class) : null;
    }

    @Override
    public UserDTO convertModelToDto(User source) {
        log.info("Parsing the {} user db entity model to dto", source);
        return source != null ? accountDataMapper.map(source, UserDTO.class) : null;
    }
}
