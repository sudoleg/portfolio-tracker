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
    public ResponseEntity<PortfolioDto> createPortfolio(
            @RequestBody PortfolioDto portfolioDto
    ) {
        PortfolioEntity savedPortfolio = portfolioService.createPortfolio(portfolioDto);
        return new ResponseEntity<>(portfolioMapper.mapToDto(savedPortfolio), HttpStatus.CREATED);
    }

    @GetMapping(path = "/portfolios")
    public List<PortfolioDto> getAllPortfolios() {
        List<PortfolioEntity> portfolioEntities = portfolioService.findAll();
        return portfolioEntities.stream().map(portfolioMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/portfolios/{id}")
    public ResponseEntity<PortfolioDto> getPortfolioById(@PathVariable Integer id) {
        Optional<PortfolioEntity> result = portfolioService.findOne(id);
        return result.map(portfolioEntity -> {
            PortfolioDto portfolioDto = portfolioMapper.mapToDto(portfolioEntity);
            return new ResponseEntity<>(portfolioDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping(path = "/portfolios/{id}")
    public ResponseEntity<PortfolioDto> fullUpdatePortfolio(
            @PathVariable Integer id,
            @RequestBody PortfolioDto portfolioDto
    ) {
        if (!portfolioService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        portfolioDto.setPortfolioId(id);
        PortfolioEntity portfolioEntity = portfolioMapper.mapFromDto(portfolioDto);

        PortfolioEntity saved = portfolioService.save(portfolioEntity);
        return new ResponseEntity<>(portfolioMapper.mapToDto(saved), HttpStatus.OK);
    }

    @PatchMapping(path = "/portfolios/{id}")
    public ResponseEntity<PortfolioDto> partialUpdatePortfolio(
            @PathVariable Integer id,
            @RequestBody PortfolioDto portfolioDto
    ) {
        if (!portfolioService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PortfolioEntity portfolioEntity = portfolioMapper.mapFromDto(portfolioDto);

        PortfolioEntity updatedPortfolioEntity = portfolioService.partialUpdate(id, portfolioEntity);
        return new ResponseEntity<>(portfolioMapper.mapToDto(updatedPortfolioEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/portfolios/{id}")
    public ResponseEntity deletePortfolio(@PathVariable Integer id) {
        if (!portfolioService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        portfolioService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
