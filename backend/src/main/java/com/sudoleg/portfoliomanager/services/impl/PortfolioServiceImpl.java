package com.sudoleg.portfoliomanager.services.impl;

import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
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

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, UserRepository userRepository) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PortfolioEntity save(PortfolioEntity portfolio) {
        return portfolioRepository.save(portfolio);
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
    public Optional<PortfolioEntity> findOne(Integer id) {
        return portfolioRepository.findById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return portfolioRepository.existsById(id);
    }

    @Override
    public PortfolioEntity partialUpdate(Integer id, PortfolioEntity portfolioEntity) {
        portfolioEntity.setPortfolioId(id);
        return portfolioRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(portfolioEntity.getName()).ifPresent(existingUser::setName);
            return portfolioRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Portfolio doesn't exist!"));
    }

    @Override
    public void delete(Integer id) {
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
    public List<PortfolioEntity> getUsersPortfolios(Integer userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return portfolioRepository.findByUserEntity_Id(userId);
    }
}