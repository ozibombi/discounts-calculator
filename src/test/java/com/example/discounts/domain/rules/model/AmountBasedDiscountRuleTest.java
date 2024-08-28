package com.example.discounts.domain.rules.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class AmountBasedDiscountRuleTest {
    @Test
    public void isApplicableShouldReturnTrueWhenQuantityBetweenLimiters() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 20);
        var priceRelatedData = new PriceRelatedData(6, BigDecimal.TEN);

        var isApplicable = rule.isApplicable(priceRelatedData);

        assertThat(isApplicable).isTrue();
    }

    @Test
    public void isApplicableShouldReturnTrueWhenQuantityEqualLowerLimiter() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 20);
        var priceRelatedData = new PriceRelatedData(5, BigDecimal.TEN);

        var isApplicable = rule.isApplicable(priceRelatedData);

        assertThat(isApplicable).isTrue();
    }

    @Test
    public void isApplicableShouldReturnFalseWhenQuantityEqualGreaterLimiter() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 20);
        var priceRelatedData = new PriceRelatedData(10, BigDecimal.TEN);

        var isApplicable = rule.isApplicable(priceRelatedData);

        assertThat(isApplicable).isFalse();
    }

    @Test
    public void isApplicableShouldReturnFalseWhenQuantityGreaterThanGreaterLimiter() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 20);
        var priceRelatedData = new PriceRelatedData(11, BigDecimal.TEN);

        var isApplicable = rule.isApplicable(priceRelatedData);

        assertThat(isApplicable).isFalse();
    }

    @Test
    public void isApplicableShouldReturnFalseWhenQuantityLowerThanLowerLimiter() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, 10, 20);
        var priceRelatedData = new PriceRelatedData(0, BigDecimal.TEN);

        var isApplicable = rule.isApplicable(priceRelatedData);

        assertThat(isApplicable).isFalse();
    }

    @Test
    public void isApplicableShouldReturnTrueWhenQuantityLowerThanGreaterLimiterAndLowerLimiterIsNull() {
        var rule = new AmountBasedPercentageDiscountRule(1, null, 10, 20);
        var priceRelatedData = new PriceRelatedData(0, BigDecimal.TEN);

        var isApplicable = rule.isApplicable(priceRelatedData);

        assertThat(isApplicable).isTrue();
    }

    @Test
    public void isApplicableShouldReturnTrueWhenQuantityGreaterThanLowerLimiterAndGreaterLimiterIsNull() {
        var rule = new AmountBasedPercentageDiscountRule(1, 5, null, 20);
        var priceRelatedData = new PriceRelatedData(100, BigDecimal.TEN);

        var isApplicable = rule.isApplicable(priceRelatedData);

        assertThat(isApplicable).isTrue();
    }

    @Test
    public void AmountBasedDiscountRuleShouldThrowIllegalArgumentExceptionWhenLimitersAreBothNull() {
        assertThatThrownBy(() -> new AmountBasedPercentageDiscountRule(1, null, null, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Both greaterThanEqual and lowerThan are null");
    }
}