package com.example.discounts.app.admin.rules.mapper;

import com.example.discounts.domain.rules.model.AmountBasedAmountDiscountRule;
import com.example.discounts.domain.rules.model.AmountBasedPercentageDiscountRule;
import com.example.discounts.domain.rules.model.DiscountRule;
import com.example.discounts.infrastructure.shared.model.AmountBasedAmountDiscountRuleDto;
import com.example.discounts.infrastructure.shared.model.AmountBasedPercentageDiscountRuleDto;
import com.example.discounts.infrastructure.shared.model.DiscountRuleDto;

public class DiscountRulesToDtoMapper {

    public DiscountRuleDto toDto(DiscountRule discountRule) {
        switch (discountRule) {
            case AmountBasedAmountDiscountRule rule -> {
                return new AmountBasedAmountDiscountRuleDto(rule.getPriority(), rule.getGreaterThanEqual(), rule.getLowerThan(), rule.getDiscountAmount());
            }
            case AmountBasedPercentageDiscountRule rule -> {
                return new AmountBasedPercentageDiscountRuleDto(rule.getPriority(), rule.getGreaterThanEqual(), rule.getLowerThan(), rule.getPercentage());
            }
        }
    }
}
