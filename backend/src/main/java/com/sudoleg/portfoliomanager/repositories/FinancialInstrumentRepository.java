package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.domain.entities.FinancialInstrumentEntity;
import org.springframework.data.repository.CrudRepository;

public interface FinancialInstrumentRepository extends CrudRepository<FinancialInstrumentEntity, Long> {
}
