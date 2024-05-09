package com.sudoleg.portfoliomanager.mappers.impl;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapperImpl implements Mapper<PortfolioEntity, PortfolioDto> {

    private ModelMapper modelMapper;

    public PortfolioMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PortfolioDto mapToDto(PortfolioEntity portfolio) {
        return modelMapper.map(portfolio, PortfolioDto.class);
    }

    @Override
    public PortfolioEntity mapFromDto(PortfolioDto portfolio) {
        return modelMapper.map(portfolio, PortfolioEntity.class);
    }

}
