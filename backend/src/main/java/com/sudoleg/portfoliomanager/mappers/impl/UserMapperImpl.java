package com.sudoleg.portfoliomanager.mappers.impl;

import com.sudoleg.portfoliomanager.domain.dto.UserDto;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper mapper) {
        this.modelMapper = mapper;

    }

    /**
     * Maps from a user entity to user DTO.
     */
    @Override
    public UserDto mapTo(UserEntity userEntity) {

        return modelMapper.map(userEntity, UserDto.class);
    }

    /**
     * Maps from a user DTO to user entity.
     */
    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

}