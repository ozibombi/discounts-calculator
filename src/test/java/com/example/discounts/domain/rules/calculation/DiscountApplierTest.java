package com.example.discounts.domain.rules.calculation;

import com.example.discounts.domain.rules.model.AmountBasedAmountDiscountRule;
import com.example.discounts.domain.rules.model.AmountBasedPercentageDiscountRule;
import com.example.discounts.domain.rules.model.DiscountRule;
import com.example.discounts.domain.rules.model.PriceRelatedData;
import com.example.discounts.domain.rules.ports.DiscountRulesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiscountApplierTest {


    private DiscountRulesRepository discountRulesRepository;
    private DiscountApplier discountApplier;

    @BeforeEach
    void setUp() {
        discountRulesRepository = mock(DiscountRulesRepository.class);
        discountApplier = new DiscountApplier(discountRulesRepository);
    }
    @Test
    void shouldReturnUnitPriceWhenNoDiscountRules() {
        // Given
        PriceRelatedData priceData = new PriceRelatedData(10, BigDecimal.valueOf(100));
        when(discountRulesRepository.getAll()).thenReturn(List.of());

        // When
        BigDecimal finalPrice = discountApplier.applyTheMostProfitableDiscount(priceData);

        // Then
        assertThat(finalPrice).isEqualByComparingTo(new BigDecimal("100.00"));
    }

    @Test
    void shouldApplySingleDiscountRule() {
        // Given
        PriceRelatedData priceData = new PriceRelatedData(10, BigDecimal.valueOf(100));
        DiscountRule discountRule = new AmountBasedAmountDiscountRule(1, 5, null, BigDecimal.valueOf(50));
        when(discountRulesRepository.getAll()).thenReturn(List.of(discountRule));

        // When
        BigDecimal finalPrice = discountApplier.applyTheMostProfitableDiscount(priceData);

        // Then
        assertThat(finalPrice).isEqualByComparingTo(new BigDecimal("950.00"));
    }

    @Test
    void shouldApplyMostProfitableDiscount() {
        // Given
        PriceRelatedData priceData = new PriceRelatedData(10, BigDecimal.valueOf(100));
        DiscountRule discountRule1 = new AmountBasedAmountDiscountRule(1, 5, null, BigDecimal.valueOf(50));
        DiscountRule discountRule2 = new AmountBasedPercentageDiscountRule(1, 5, null, 10);
        when(discountRulesRepository.getAll()).thenReturn(List.of(discountRule1, discountRule2));

        // When
        BigDecimal finalPrice = discountApplier.applyTheMostProfitableDiscount(priceData);

        // Then
        assertThat(finalPrice).isEqualByComparingTo(new BigDecimal("900.00"));
    }

    @Test
    void shouldApplyRuleWithHighestPriority() {
        // Given
        PriceRelatedData priceData = new PriceRelatedData(10, BigDecimal.valueOf(100));
        DiscountRule discountRule1 = new AmountBasedAmountDiscountRule(1, 5, null, BigDecimal.valueOf(50));
        DiscountRule discountRule2 = new AmountBasedPercentageDiscountRule(2, 5, null, 10);
        when(discountRulesRepository.getAll()).thenReturn(List.of(discountRule1, discountRule2));

        // When
        BigDecimal finalPrice = discountApplier.applyTheMostProfitableDiscount(priceData);

        // Then
        assertThat(finalPrice).isEqualByComparingTo(new BigDecimal("950.00"));
    }
}