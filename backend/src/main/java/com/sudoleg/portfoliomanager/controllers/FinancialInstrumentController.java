package com.sudoleg.portfoliomanager.controllers;

import com.sudoleg.portfoliomanager.domain.dto.FinancialInstrumentDto;
import com.sudoleg.portfoliomanager.domain.entities.FinancialInstrumentEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.services.FinancialInstrumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/securities")
public class FinancialInstrumentController {

    private final Mapper<FinancialInstrumentEntity, FinancialInstrumentDto> mapper;
    private final FinancialInstrumentService financialInstrumentService;

    public FinancialInstrumentController(Mapper<FinancialInstrumentEntity, FinancialInstrumentDto> mapper, FinancialInstrumentService financialInstrumentService) {
        this.mapper = mapper;
        this.financialInstrumentService = financialInstrumentService;
    }

    /**
     * Creates a new financial instrument/security.
     *
     * @param requestBody the DTO containing the details of the financial instrument to be created.
     * @return ResponseEntity containing the created financial instrument DTO and HTTP status 201 (Created).
     */
    @PostMapping(path = "")
    public ResponseEntity<FinancialInstrumentDto> createSecurity(
            @RequestBody FinancialInstrumentDto requestBody
    ) {
        FinancialInstrumentEntity financialInstrumentEntity = financialInstrumentService.save(requestBody);
        FinancialInstrumentDto responseBody = mapper.mapToDto(financialInstrumentEntity);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    /**
     * Retrieves a financial instrument by its ID.
     *
     * @param id the ID of the financial instrument to be retrieved.
     * @return ResponseEntity containing the financial instrument DTO and HTTP status 200 (OK) if found,
     * or HTTP status 404 (Not Found) if not found.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<FinancialInstrumentDto> getSecurityById(
            @PathVariable Long id
    ) {
        Optional<FinancialInstrumentEntity> result = financialInstrumentService.findOne(id);
        return result.map(financialInstrumentEntity -> {
            FinancialInstrumentDto financialInstrumentDto = mapper.mapToDto(financialInstrumentEntity);
            return new ResponseEntity<>(financialInstrumentDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves all financial instruments saved in the database.
     *
     * @return ResponseEntity containing a list of all financial instrument DTOs and HTTP status 200 (OK).
     */
    @GetMapping(path = "")
    public ResponseEntity<List<FinancialInstrumentDto>> getAllSecurities() {
        List<FinancialInstrumentEntity> financialInstrumentEntities = financialInstrumentService.findAll();
        List<FinancialInstrumentDto> financialInstrumentDtos = financialInstrumentEntities.
                stream().
                map(mapper::mapToDto)
                .toList();
        return new ResponseEntity<>(financialInstrumentDtos, HttpStatus.OK);
    }

}
