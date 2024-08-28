package com.example.discounts.app.prices.calculation;

import com.example.discounts.app.prices.exceptions.ProductNotFoundException;
import com.example.discounts.app.prices.model.ProductDiscountedPriceDto;
import com.example.discounts.domain.rules.calculation.DiscountApplier;
import com.example.discounts.domain.rules.model.PriceRelatedData;
import com.example.discounts.infrastructure.mongo.products.ProductsDatabase;

import java.math.BigDecimal;
import java.util.UUID;

public class PriceDiscountCalculator implements PriceCalculator {

    private final DiscountApplier discountApplier;
    private final ProductsDatabase productsDatabase;

    public PriceDiscountCalculator(DiscountApplier discountApplier, ProductsDatabase productsDatabase) {
        this.discountApplier = discountApplier;
        this.productsDatabase = productsDatabase;
    }

    @Override
    public ProductDiscountedPriceDto calculate(UUID productId, int amount) {
        if (amount == 0) return new ProductDiscountedPriceDto(productId, BigDecimal.ZERO);
        var product = productsDatabase.findById(productId).orElseThrow(ProductNotFoundException::new);
        var priceRelatedData = new PriceRelatedData(amount, product.unitPrice());
        var price = discountApplier.applyTheMostProfitableDiscount(priceRelatedData);
        return new ProductDiscountedPriceDto(productId, price);
    }
}

