package com.example.discounts.domain.rules.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public final class AmountBasedPercentageDiscountRule extends AmountBasedDiscountRule {

    private final int percentage;

    public AmountBasedPercentageDiscountRule(int priority, Integer greaterThanEqual, Integer lowerThan, int percentage) {
        super(priority, greaterThanEqual, lowerThan);
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("percentage must be between 0 and 100");
        }
        this.percentage = percentage;
    }


    @Override
    public boolean isApplicable(PriceRelatedData priceRelatedData) {
        return isApplicableInTermsOfQuantity(priceRelatedData.quantity());
    }

    @Override
    public BigDecimal getPriceAfterDiscount(PriceRelatedData priceRelatedData) {
        return priceRelatedData.unitPrice()
                .multiply(BigDecimal.valueOf(priceRelatedData.quantity()))
                .multiply(new BigDecimal("100.00").subtract(BigDecimal.valueOf(percentage)))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
