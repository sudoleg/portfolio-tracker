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

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final Mapper<PortfolioEntity, PortfolioDto> portfolioMapper;

    public PortfolioController(PortfolioService portfolioService,
            Mapper<PortfolioEntity, PortfolioDto> portfolioMapper) {
        this.portfolioService = portfolioService;
        this.portfolioMapper = portfolioMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PortfolioDto> getPortfolioById(@PathVariable Long id) {
        Optional<PortfolioEntity> result = portfolioService.findOne(id);
        return result.map(portfolioEntity -> {
            PortfolioDto portfolioDto = portfolioMapper.mapToDto(portfolioEntity);
            return new ResponseEntity<>(portfolioDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieve all portfolios. If userId is provided (as request parameter),
     * only portfolios owned by a user are returned.
     * Else all existing portfolios are returned.
     *
     * @return List of portfolios
     */
    @GetMapping(path = "")
    public ResponseEntity<List<PortfolioDto>> getAllPortfolios(
            @RequestParam(value = "userId", required = false) Long userId) {
        List<PortfolioEntity> portfolioEntities;

        if (userId != null) {
            portfolioEntities = portfolioService.getUsersPortfolios(userId);
        } else {
            portfolioEntities = portfolioService.findAll();
        }

        List<PortfolioDto> portfolioDTOs = portfolioEntities.stream().map(portfolioMapper::mapToDto).toList();
        return new ResponseEntity<>(portfolioDTOs, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<PortfolioDto> createPortfolio(
            @RequestBody PortfolioDto portfolioDto) {
        PortfolioEntity savedPortfolio = portfolioService.createPortfolio(portfolioDto);
        return new ResponseEntity<>(portfolioMapper.mapToDto(savedPortfolio), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PortfolioDto> fullUpdatePortfolio(
            @PathVariable Long id,
            @RequestBody PortfolioDto portfolioDto) {
        portfolioDto.setId(id);
        PortfolioEntity savedPortfolio = portfolioService.save(id, portfolioDto);
        return new ResponseEntity<>(portfolioMapper.mapToDto(savedPortfolio), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long id) {
        portfolioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
