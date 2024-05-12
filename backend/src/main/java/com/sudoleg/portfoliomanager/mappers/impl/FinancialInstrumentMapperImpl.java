package com.sudoleg.portfoliomanager.mappers.impl;

import com.sudoleg.portfoliomanager.domain.dto.FinancialInstrumentDto;
import com.sudoleg.portfoliomanager.domain.entities.FinancialInstrumentEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FinancialInstrumentMapperImpl implements Mapper<FinancialInstrumentEntity, FinancialInstrumentDto> {

    private ModelMapper modelMapper;

    public FinancialInstrumentMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FinancialInstrumentDto mapToDto(FinancialInstrumentEntity financialInstrumentEntity) {
        return modelMapper.map(financialInstrumentEntity, FinancialInstrumentDto.class);
    }

    @Override
    public FinancialInstrumentEntity mapFromDto(FinancialInstrumentDto financialInstrumentDto) {
        return modelMapper.map(financialInstrumentDto, FinancialInstrumentEntity.class);
    }
}
