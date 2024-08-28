package com.example.discounts.domain.rules.model;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public final class AmountBasedAmountDiscountRule extends AmountBasedDiscountRule {

    private final BigDecimal discountAmount;

    public AmountBasedAmountDiscountRule(int priority, Integer greaterThanEqual, Integer lowerThan, BigDecimal discountAmount) {
        super(priority, greaterThanEqual, lowerThan);
        this.discountAmount = discountAmount;
    }


    @Override
    public boolean isApplicable(PriceRelatedData priceRelatedData) {
        return isApplicableInTermsOfQuantity(priceRelatedData.quantity());
    }

    @Override
    public BigDecimal getPriceAfterDiscount(PriceRelatedData priceRelatedData) {
        var total = priceRelatedData.unitPrice()
                .multiply(BigDecimal.valueOf(priceRelatedData.quantity()))
                .subtract(discountAmount);
        return total.max(BigDecimal.ZERO);
    }
}
