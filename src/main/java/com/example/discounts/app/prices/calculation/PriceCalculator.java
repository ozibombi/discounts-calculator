package com.example.discounts.app.prices.calculation;

import com.example.discounts.app.prices.model.ProductDiscountedPriceDto;

import java.util.UUID;

public interface PriceCalculator {

    ProductDiscountedPriceDto calculate(UUID productId, int amount);
}
