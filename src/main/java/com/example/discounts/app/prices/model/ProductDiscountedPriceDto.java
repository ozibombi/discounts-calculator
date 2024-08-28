package com.example.discounts.app.prices.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDiscountedPriceDto(
        UUID productId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00") BigDecimal price
) {}
