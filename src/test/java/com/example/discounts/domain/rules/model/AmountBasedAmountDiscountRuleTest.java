package com.example.discounts.domain.rules.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AmountBasedAmountDiscountRuleTest {

    @Test
    public void testGetPriceAfterDiscountForGivenDiscount() {
        var rule = new AmountBasedAmountDiscountRule(1, 0, 10, BigDecimal.TEN);
        var priceRelatedData = new PriceRelatedData(3, BigDecimal.TEN);

        var discountedPrice = rule.getPriceAfterDiscount(priceRelatedData);

        assertThat(discountedPrice).isEqualByComparingTo(new BigDecimal("20.00"));
    }

    @Test
    public void getPriceAfterDiscountShouldReturn0WhenDiscountIsBiggerThanTotalPrice() {
        var rule = new AmountBasedAmountDiscountRule(1, 0, 10, BigDecimal.TEN);
        var priceRelatedData = new PriceRelatedData(3, BigDecimal.ONE);

        var discountedPrice = rule.getPriceAfterDiscount(priceRelatedData);

        assertThat(discountedPrice).isEqualByComparingTo(new BigDecimal("0.00"));
    }
}