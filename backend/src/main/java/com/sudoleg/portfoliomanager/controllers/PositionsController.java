package com.sudoleg.portfoliomanager.controllers;

import com.sudoleg.portfoliomanager.domain.dto.PositionDto;
import com.sudoleg.portfoliomanager.domain.entities.PositionEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/positions")
public class PositionsController {

    private final Mapper<PositionEntity, PositionDto> mapper;
    private final PositionService positionService;

    public PositionsController(Mapper<PositionEntity, PositionDto> mapper, PositionService positionService) {
        this.mapper = mapper;
        this.positionService = positionService;
    }

    @PostMapping(path = "")
    public ResponseEntity<PositionDto> createPosition(
            @RequestBody PositionDto requestBody
    ) {
        PositionEntity positionEntity = positionService.save(requestBody);
        return new ResponseEntity<>(mapper.mapToDto(positionEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PositionDto> getPositionById(
            @PathVariable Long id
    ) {
        Optional<PositionEntity> result = positionService.findOne(id);
        return result.map(positionEntity -> {
            PositionDto positionDto = mapper.mapToDto(positionEntity);
            return new ResponseEntity<>(positionDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "")
    public ResponseEntity<List<PositionDto>> getAllPositions(
            @RequestParam(value = "portfolioId", required = false) Integer portfolioId
    ) {
        List<PositionEntity> positionEntities;

        if (portfolioId != null) {
            positionEntities = positionService.findAllByPortfolioId(portfolioId);
        } else {
            positionEntities = positionService.findAll();
        }

        List<PositionDto> positionDTOs = positionEntities.stream().map(mapper::mapToDto).toList();
        return new ResponseEntity<>(positionDTOs, HttpStatus.OK);
    }

}
