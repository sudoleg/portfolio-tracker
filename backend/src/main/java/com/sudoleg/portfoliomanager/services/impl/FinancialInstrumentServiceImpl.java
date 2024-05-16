package com.sudoleg.portfoliomanager.services.impl;

import com.sudoleg.portfoliomanager.domain.dto.FinancialInstrumentDto;
import com.sudoleg.portfoliomanager.domain.entities.FinancialInstrumentEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.repositories.FinancialInstrumentRepository;
import com.sudoleg.portfoliomanager.services.FinancialInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FinancialInstrumentServiceImpl implements FinancialInstrumentService {

    @Autowired
    private final FinancialInstrumentRepository financialInstrumentRepository;

    private final Mapper<FinancialInstrumentEntity, FinancialInstrumentDto> mapper;

    public FinancialInstrumentServiceImpl(FinancialInstrumentRepository financialInstrumentRepository, Mapper<FinancialInstrumentEntity, FinancialInstrumentDto> mapper) {
        this.financialInstrumentRepository = financialInstrumentRepository;
        this.mapper = mapper;
    }

    @Override
    public FinancialInstrumentEntity save(FinancialInstrumentDto financialInstrumentDto) {
        FinancialInstrumentEntity financialInstrumentEntity = mapper.mapFromDto(financialInstrumentDto);
        return financialInstrumentRepository.save(financialInstrumentEntity);
    }

    @Override
    public Optional<FinancialInstrumentEntity> findOne(Long id) {
        return financialInstrumentRepository.findById(id);
    }

    @Override
    public List<FinancialInstrumentEntity> findAll() {
        return StreamSupport.stream(financialInstrumentRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

}
