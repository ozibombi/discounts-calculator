package com.example.discounts.domain.rules.model;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public sealed abstract class DiscountRule permits AmountBasedDiscountRule {

    private final int priority;

    public DiscountRule(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public abstract boolean isApplicable(PriceRelatedData priceRelatedData);

    public abstract BigDecimal getPriceAfterDiscount(PriceRelatedData priceRelatedData);
}