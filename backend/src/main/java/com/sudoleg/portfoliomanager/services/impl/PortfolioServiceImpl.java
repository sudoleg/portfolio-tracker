package com.sudoleg.portfoliomanager.services.impl;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.mappers.Mapper;
import com.sudoleg.portfoliomanager.repositories.PortfolioRepository;
import com.sudoleg.portfoliomanager.repositories.UserRepository;
import com.sudoleg.portfoliomanager.services.PortfolioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRepository portfolioRepository;
    private UserRepository userRepository;
    private Mapper<PortfolioEntity, PortfolioDto> portfolioMapper;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, UserRepository userRepository,
            Mapper<PortfolioEntity, PortfolioDto> portfolioMapper) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
        this.portfolioMapper = portfolioMapper;
    }

    @Override
    public PortfolioEntity save(Long portfolioId, PortfolioDto portfolioDto) {
        if (!portfolioRepository.existsById(portfolioId)) {
            throw new EntityNotFoundException("Portfolio not found!");
        }

        UserEntity userEntity = userRepository.findById(portfolioDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        PortfolioEntity portfolioEntity = portfolioMapper.mapFromDto(portfolioDto);
        portfolioEntity.setUserEntity(userEntity);

        return portfolioRepository.save(portfolioEntity);
    }

    @Override
    public List<PortfolioEntity> findAll() {
        return StreamSupport.stream(portfolioRepository
                .findAll()
                .spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PortfolioEntity> findOne(Long id) {
        return portfolioRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return portfolioRepository.existsById(id);
    }

    @Override
    public PortfolioEntity partialUpdate(Long portfolioId, PortfolioDto portfolioDto) {
        if (!portfolioRepository.existsById(portfolioId)) {
            throw new EntityNotFoundException("Portfolio not found!");
        }

        PortfolioEntity portfolioEntity = portfolioMapper.mapFromDto(portfolioDto);
        portfolioEntity.setId(portfolioId);

        return portfolioRepository.findById(portfolioId).map(existingUser -> {
            Optional.ofNullable(portfolioEntity.getName()).ifPresent(existingUser::setName);
            return portfolioRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Portfolio doesn't exist!"));
    }

    @Override
    public void delete(Long id) {
        portfolioRepository.deleteById(id);
    }

    @Override
    public PortfolioEntity createPortfolio(PortfolioDto requestDTO) {
        UserEntity userEntity = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        PortfolioEntity portfolioEntity = PortfolioEntity.builder()
                .name(requestDTO.getName())
                .userEntity(userEntity)
                .build();

        return portfolioRepository.save(portfolioEntity);
    }

    @Override
    public List<PortfolioEntity> getUsersPortfolios(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return portfolioRepository.findByUserEntity_Id(userId);
    }
}