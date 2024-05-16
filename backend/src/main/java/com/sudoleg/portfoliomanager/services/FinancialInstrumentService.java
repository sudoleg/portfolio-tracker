package com.sudoleg.portfoliomanager.services;

import com.sudoleg.portfoliomanager.domain.dto.FinancialInstrumentDto;
import com.sudoleg.portfoliomanager.domain.entities.FinancialInstrumentEntity;

import java.util.List;
import java.util.Optional;

public interface FinancialInstrumentService {

    FinancialInstrumentEntity save(FinancialInstrumentDto financialInstrumentDto);

    Optional<FinancialInstrumentEntity> findOne(Long id);

    List<FinancialInstrumentEntity> findAll();

}
