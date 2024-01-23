package com.sudoleg.portfoliomanager.controllers;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.services.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {

    private PortfolioService portfolioService;

    private Mapper<PortfolioEntity, PortfolioDto> portfolioMapper;

    public PortfolioController(PortfolioService portfolioService, Mapper<PortfolioEntity, PortfolioDto> portfolioMapper) {
        this.portfolioService = portfolioService;
        this.portfolioMapper = portfolioMapper;
    }

    @PostMapping(path = "/portfolios")
    private ResponseEntity<PortfolioDto> createPortfolio(@RequestBody PortfolioDto portfolioDto) {
        PortfolioEntity portfolioEntity = portfolioMapper.mapFrom(portfolioDto);
        PortfolioEntity savedPortfolio = portfolioService.createPortfolio(portfolioEntity);
        return new ResponseEntity<>(portfolioMapper.mapTo(savedPortfolio), HttpStatus.CREATED);
    }


}
