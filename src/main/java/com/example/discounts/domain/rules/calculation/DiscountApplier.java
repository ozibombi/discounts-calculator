package com.example.discounts.domain.rules.calculation;

import com.example.discounts.domain.rules.model.DiscountRule;
import com.example.discounts.domain.rules.model.PriceRelatedData;
import com.example.discounts.domain.rules.ports.DiscountRulesRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DiscountApplier {

    private final DiscountRulesRepository discountRulesRepository;

    public DiscountApplier(DiscountRulesRepository discountRulesRepository) {
        this.discountRulesRepository = discountRulesRepository;
    }

    public BigDecimal applyTheMostProfitableDiscount(PriceRelatedData priceRelatedData) {
        var allApplicableRules = discountRulesRepository.getAll()
                .stream()
                .filter(dr -> dr.isApplicable(priceRelatedData))
                .toList();
        var rulesWithHighestImportance = filterAndReturnTheMostAccurate(allApplicableRules);
        return findTheMostProfitablePrice(rulesWithHighestImportance, priceRelatedData)
                .orElse(priceRelatedData.unitPrice());
    }

    private Optional<BigDecimal> findTheMostProfitablePrice(
            Stream<DiscountRule> discountRules,
            PriceRelatedData priceRelatedData
    ) {
        return discountRules
                .map(r -> r.getPriceAfterDiscount(priceRelatedData))
                .min(BigDecimal::compareTo)
                .map(bc -> bc.setScale(2, RoundingMode.HALF_UP));
    }

    private Stream<DiscountRule> filterAndReturnTheMostAccurate(
            List<DiscountRule> discountRules
    ) {
        var highestImportance = discountRules
                .stream()
                .mapToInt(DiscountRule::getPriority)
                .min();
        return discountRules
                .stream()
                .filter(dr -> dr.getPriority() == highestImportance.getAsInt());
    }
}