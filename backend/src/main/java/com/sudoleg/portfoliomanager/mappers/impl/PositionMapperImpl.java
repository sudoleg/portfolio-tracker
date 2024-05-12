package com.sudoleg.portfoliomanager.mappers.impl;

import com.sudoleg.portfoliomanager.domain.dto.PositionDto;
import com.sudoleg.portfoliomanager.domain.entities.PositionEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class PositionMapperImpl implements Mapper<PositionEntity, PositionDto> {

    private final ModelMapper modelMapper;

    public PositionMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PositionDto mapToDto(PositionEntity positionEntity) {
        return modelMapper.map(positionEntity, PositionDto.class);
    }

    @Override
    public PositionEntity mapFromDto(PositionDto positionDto) {
        return modelMapper.map(positionDto, PositionEntity.class);
    }
}
