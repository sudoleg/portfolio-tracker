package com.sudoleg.portfoliomanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
import com.sudoleg.portfoliomanager.domain.entities.PortfolioEntity;
import com.sudoleg.portfoliomanager.domain.entities.UserEntity;
import com.sudoleg.portfoliomanager.services.PortfolioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PortfolioControllerIntegrationTest {

        private final MockMvc mockMvc;
        private final ObjectMapper objectMapper;
        private final PortfolioService portfolioService;

        @Autowired
        public PortfolioControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper,
                        PortfolioService portfolioService) {
                this.mockMvc = mockMvc;
                this.objectMapper = objectMapper;
                this.portfolioService = portfolioService;
        }

        @Test
        public void testPortfolioCreationReturnsCorrectStatusCode() throws Exception {
                PortfolioDto portfolioDto = TestDataUtil.createTestPortfolioDtoA(null);
                portfolioDto.setId(null);
                String portfolioJson = objectMapper.writeValueAsString(portfolioDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/portfolios")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(portfolioJson))
                                .andExpect(
                                                MockMvcResultMatchers.status().isCreated());
        }

        @Test
        public void testPortfolioCreationReturnsSavedPortfolio() throws Exception {
                PortfolioDto portfolioDto = TestDataUtil.createTestPortfolioDtoA(null);
                portfolioDto.setId(null);
                String portfolioJson = objectMapper.writeValueAsString(portfolioDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/portfolios")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(portfolioJson))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.portfolioId").isNumber())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.name").value(portfolioDto.getName()))
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.user").isEmpty());
        }

        /*
         * @Test
         * public void testFindAllPortfoliosReturnsCorrectList() throws Exception {
         * UserEntity userA = TestDataUtil.createTestUserA();
         * PortfolioEntity portfolioA = TestDataUtil.createTestPortfolioEntityA(userA);
         * PortfolioEntity portfolioB = TestDataUtil.createTestPortfolioB(userA);
         * 
         * portfolioService.save(portfolioA);
         * portfolioService.save(portfolioB);
         * 
         * mockMvc.perform(
         * MockMvcRequestBuilders.get("/portfolios"))
         * .andExpect(
         * MockMvcResultMatchers.jsonPath("[0].portfolioId")
         * .value(portfolioA.getId()))
         * .andExpect(
         * MockMvcResultMatchers.jsonPath("[0].name").value(portfolioA.getName()));
         * }
         */

        @Test
        public void testFindOnePortfolioReturns404IfNotExist() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/portfolios/156"))
                                .andExpect(
                                                MockMvcResultMatchers.status().isNotFound());
        }

        /*
         * @Test
         * public void testFindOnePortfolioReturnsCorrectResponse() throws Exception {
         * UserEntity userA = TestDataUtil.createTestUserA();
         * PortfolioEntity portfolioA = TestDataUtil.createTestPortfolioEntityA(userA);
         * portfolioService.save(portfolioA);
         * 
         * mockMvc.perform(
         * MockMvcRequestBuilders.get("/portfolios/1"))
         * .andExpect(
         * MockMvcResultMatchers.status().isOk())
         * .andExpect(
         * MockMvcResultMatchers.jsonPath("$.portfolioId")
         * .value(portfolioA.getId()))
         * .andExpect(
         * MockMvcResultMatchers.jsonPath("$.name").value(portfolioA.getName()));
         * }
         */

        /*
         * @Test
         * public void testPortfolioFullUpdateReturnsUpdatedPortfolio() throws Exception
         * {
         * PortfolioEntity portfolioEntity =
         * TestDataUtil.createTestPortfolioEntityA(null);
         * PortfolioEntity savedPortfolio = portfolioService.save(portfolioEntity);
         * 
         * PortfolioDto portfolioDto = TestDataUtil.createTestPortfolioDtoA(null);
         * portfolioDto.setName("UPDATED");
         * String portfolioJson = objectMapper.writeValueAsString(portfolioDto);
         * 
         * mockMvc.perform(
         * MockMvcRequestBuilders.put("/portfolios/" + portfolioDto.getId())
         * .contentType(MediaType.APPLICATION_JSON)
         * .content(portfolioJson))
         * .andExpect(
         * MockMvcResultMatchers.status().isOk())
         * .andExpect(
         * MockMvcResultMatchers.jsonPath(
         * "$.portfolioId").value(savedPortfolio.getId()))
         * .andExpect(
         * MockMvcResultMatchers.jsonPath("$.name").value(portfolioDto.getName()));
         * }
         */

        /*
         * @Test
         * public void testPartialUpdateOnExistingPortfolioReturnsHttpOk() throws
         * Exception {
         * PortfolioEntity savedPortfolio =
         * TestDataUtil.createTestPortfolioEntityA(null);
         * portfolioService.save(savedPortfolio);
         * PortfolioDto testPortfolioDto = TestDataUtil.createTestPortfolioDtoA(null);
         * 
         * testPortfolioDto.setName("UPDATED");
         * String portfolioDtoJson = objectMapper.writeValueAsString(testPortfolioDto);
         * 
         * mockMvc.perform(
         * MockMvcRequestBuilders.patch("/portfolios/" + savedPortfolio.getId())
         * .contentType(MediaType.APPLICATION_JSON)
         * .content(portfolioDtoJson))
         * .andExpect(MockMvcResultMatchers.status().isOk());
         * }
         */

        /*
         * @Test
         * public void testPartialUpdateOnExistingPortfolioReturnsUpdatedPortfolio()
         * throws Exception {
         * PortfolioEntity savedPortfolio =
         * TestDataUtil.createTestPortfolioEntityA(null);
         * portfolioService.save(savedPortfolio);
         * PortfolioDto testPortfolioDto = TestDataUtil.createTestPortfolioDtoA(null);
         * 
         * testPortfolioDto.setName("UPDATED");
         * String portfolioDtoJson = objectMapper.writeValueAsString(testPortfolioDto);
         * 
         * mockMvc.perform(
         * MockMvcRequestBuilders.patch("/portfolios/" + savedPortfolio.getId())
         * .contentType(MediaType.APPLICATION_JSON)
         * .content(portfolioDtoJson))
         * .andExpect(
         * MockMvcResultMatchers.jsonPath("$.name").value("UPDATED"))
         * .andExpect(
         * MockMvcResultMatchers.jsonPath("$.portfolioId")
         * .value(savedPortfolio.getId()));
         * }
         */

        @Test
        public void testDeleteNonExistentPortfolioReturns404() throws Exception {
                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/portfolios/156"))
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        /*
         * @Test
         * public void testDeleteUserReturns204NoContent() throws Exception {
         * PortfolioEntity portfolioEntity =
         * TestDataUtil.createTestPortfolioEntityA(null);
         * PortfolioEntity savedPortfolio = portfolioService.save(portfolioEntity);
         * mockMvc.perform(
         * MockMvcRequestBuilders.delete("/portfolios/" +
         * savedPortfolio.getId())).andExpect(
         * MockMvcResultMatchers.status().isNoContent());
         * Optional<PortfolioEntity> result =
         * portfolioService.findOne(portfolioEntity.getId());
         * assertThat(result).isEmpty();
         * }
         */

}
