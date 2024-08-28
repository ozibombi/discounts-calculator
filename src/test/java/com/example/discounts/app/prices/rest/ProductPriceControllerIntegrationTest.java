package com.example.discounts.app.prices.rest;

import com.example.discounts.infrastructure.mongo.products.ProductsMongoDbRepository;
import com.example.discounts.infrastructure.mongo.products.entity.ProductEntity;
import com.example.discounts.infrastructure.mongo.rules.entity.DiscountRuleEntity;
import com.example.discounts.infrastructure.shared.model.AmountBasedAmountDiscountRuleDto;
import com.example.discounts.infrastructure.shared.model.AmountBasedPercentageDiscountRuleDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ExtendWith(SpringExtension.class)
class ProductPriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductsMongoDbRepository productsMongoDbRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final UUID PRODUCT_ID = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        var product = new ProductEntity(PRODUCT_ID, "Dummy", BigDecimal.TEN);
        var amountDiscountRuleDto = new AmountBasedAmountDiscountRuleDto(1, 1, 10, BigDecimal.ONE);
        var amountDiscountRule = new DiscountRuleEntity(UUID.randomUUID(), "test", Instant.now(), null, null, amountDiscountRuleDto);
        var percentageDiscountRuleDto = new AmountBasedPercentageDiscountRuleDto(0, 1, 10, 50);
        var percentageDiscountRule = new DiscountRuleEntity(UUID.randomUUID(), "test", Instant.now(), null, null, percentageDiscountRuleDto);
        productsMongoDbRepository.save(product);
        mongoTemplate.save(amountDiscountRule);
        mongoTemplate.save(percentageDiscountRule);
    }

    @AfterEach
    void tearDown() {
        productsMongoDbRepository.deleteAll();
    }


    @Test
    void shouldCalculateDiscountedPrice() throws Exception {
        // Given
        int amount = 5;
        var discountedPrice = new BigDecimal("25.00");

        mockMvc.perform(get("/api/products/{id}/prices", PRODUCT_ID)
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(discountedPrice))
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID.toString()));
    }

    @Test
    void shouldReturnBadRequestForInvalidAmount() throws Exception {
        // Given
        int invalidAmount = -1;

        // When & Then
        mockMvc.perform(get("/api/products/{id}/prices", PRODUCT_ID)
                        .param("amount", String.valueOf(invalidAmount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnPriceZeroWhenAmountIsZero() throws Exception {
        // Given
        int amount = 0;

        // When & Then
        mockMvc.perform(get("/api/products/{id}/prices", PRODUCT_ID)
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(BigDecimal.ZERO))
                .andExpect(jsonPath("$.productId").value(PRODUCT_ID.toString()));
    }

    @Test
    void shouldReturnNotFoundResponseWhenProductNotFound() throws Exception {
        // Given
        var productId = UUID.randomUUID();

        // When & Then
        mockMvc.perform(get("/api/products/{id}/prices", productId)
                        .param("amount", "4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}