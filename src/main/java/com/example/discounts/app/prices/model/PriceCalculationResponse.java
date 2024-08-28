package com.example.discounts.app.prices.model;

import java.util.List;

public record PriceCalculationResponse(
        List<ProductDiscountedPriceDto> productPrices
) {
}
