package com.example.discounts.infrastructure.shared.model;

import com.example.discounts.domain.rules.model.DiscountRule;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AmountBasedAmountDiscountRuleDto.class, name = "AMOUNT_BASED_AMOUNT_DISCOUNT"),
        @JsonSubTypes.Type(value = AmountBasedPercentageDiscountRuleDto.class, name = "AMOUNT_BASED_PERCENTAGE_DISCOUNT")
})
public sealed abstract class DiscountRuleDto permits AmountBasedAmountDiscountRuleDto, AmountBasedPercentageDiscountRuleDto {

    private final int priority;

    public DiscountRuleDto(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public abstract DiscountRule toModel();
}