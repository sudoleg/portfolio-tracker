package com.sudoleg.portfoliomanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sudoleg.portfoliomanager.TestDataUtil;
import com.sudoleg.portfoliomanager.domain.dto.PortfolioDto;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PortfolioControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public PortfolioControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testPortfolioCreationReturnsCorrectStatusCode() throws Exception {
        PortfolioDto portfolioDto = TestDataUtil.createTestPortfolioDtoA(null);
        portfolioDto.setPortfolioId(null);
        String portfolioJson = objectMapper.writeValueAsString(portfolioDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/portfolios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(portfolioJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testPortfolioCreationReturnsSavedPortfolio() throws Exception {
        PortfolioDto portfolioDto = TestDataUtil.createTestPortfolioDtoA(null);
        portfolioDto.setPortfolioId(null);
        String portfolioJson = objectMapper.writeValueAsString(portfolioDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/portfolios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(portfolioJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.portfolioId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(portfolioDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.user").isEmpty()
        );
    }

}
