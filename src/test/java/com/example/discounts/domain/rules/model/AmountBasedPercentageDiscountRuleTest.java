package com.example.discounts.domain.rules.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class AmountBasedPercentageDiscountRuleTest {

    @Test
    public void testGetPriceAfterDiscountForGivenPercentage() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 20);
        var priceRelatedData = new PriceRelatedData(1, BigDecimal.TEN);

        var discountedPrice = rule.getPriceAfterDiscount(priceRelatedData);

        assertThat(discountedPrice).isEqualByComparingTo(new BigDecimal("8.00"));
    }

    @Test
    public void getPriceAfterDiscountShouldReturn0ForPercentage100() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 100);
        var priceRelatedData = new PriceRelatedData(1, BigDecimal.TEN);

        var discountedPrice = rule.getPriceAfterDiscount(priceRelatedData);

        assertThat(discountedPrice).isEqualByComparingTo(new BigDecimal("0.00"));
    }

    @Test
    public void getPriceAfterDiscountShouldReturnOriginalPriceForPercentage0() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 0);
        var priceRelatedData = new PriceRelatedData(1, BigDecimal.TEN);

        var discountedPrice = rule.getPriceAfterDiscount(priceRelatedData);

        assertThat(discountedPrice).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    public void amountBasedPercentageDiscountRuleShouldThrowExceptionForPercentageLowerThan0() {

        assertThatThrownBy(() -> new AmountBasedPercentageDiscountRule(1, 5, 10, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("percentage must be between 0 and 100");
    }
    @Test
    public void amountBasedPercentageDiscountRuleShouldThrowExceptionForPercentageGreaterThan100() {

        assertThatThrownBy(() -> new AmountBasedPercentageDiscountRule(1, 5, 10, 101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("percentage must be between 0 and 100");
    }
}