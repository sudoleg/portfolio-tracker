package com.sudoleg.portfoliomanager.repositories;

import com.sudoleg.portfoliomanager.domain.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio, Integer> {
}
