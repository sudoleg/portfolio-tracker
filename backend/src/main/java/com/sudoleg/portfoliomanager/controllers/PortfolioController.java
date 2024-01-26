package com.sudoleg.portfoliomanager.controllers;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.services.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PortfolioController {

    private PortfolioService portfolioService;

    private Mapper<PortfolioEntity, PortfolioDto> portfolioMapper;

    public PortfolioController(PortfolioService portfolioService, Mapper<PortfolioEntity, PortfolioDto> portfolioMapper) {
        this.portfolioService = portfolioService;
        this.portfolioMapper = portfolioMapper;
    }

    @PostMapping(path = "/portfolios")
    public ResponseEntity<PortfolioDto> createPortfolio(@RequestBody PortfolioDto portfolioDto) {
        PortfolioEntity portfolioEntity = portfolioMapper.mapFrom(portfolioDto);
        PortfolioEntity savedPortfolio = portfolioService.createPortfolio(portfolioEntity);
        return new ResponseEntity<>(portfolioMapper.mapTo(savedPortfolio), HttpStatus.CREATED);
    }

    @GetMapping(path = "/portfolios")
    public List<PortfolioDto> getAllPortfolios() {
        List<PortfolioEntity> portfolioEntities = portfolioService.findAll();
        return portfolioEntities.stream().map(portfolioMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/portfolios/{id}")
    public ResponseEntity<PortfolioDto> getPortfolioById(@PathVariable Integer id) {
        Optional<PortfolioEntity> result = portfolioService.findOne(id);
        return result.map(portfolioEntity -> {
            PortfolioDto portfolioDto = portfolioMapper.mapTo(portfolioEntity);
            return new ResponseEntity<>(portfolioDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }


}
