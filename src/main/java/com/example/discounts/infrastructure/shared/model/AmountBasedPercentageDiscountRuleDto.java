package com.example.discounts.infrastructure.shared.model;

import com.example.discounts.domain.rules.model.AmountBasedPercentageDiscountRule;
import com.example.discounts.domain.rules.model.DiscountRule;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public non-sealed class AmountBasedPercentageDiscountRuleDto extends DiscountRuleDto {

    private final Integer greaterThanEqual;
    private final Integer lowerThan;
    private final int percentage;

    public AmountBasedPercentageDiscountRuleDto(
            @JsonProperty("priority") int priority,
            @JsonProperty("greaterThanEqual") Integer greaterThanEqual,
            @JsonProperty("lowerThan")Integer lowerThan,
            @JsonProperty("percentage") int percentage
    ) {
        super(priority);
        this.greaterThanEqual = greaterThanEqual;
        this.lowerThan = lowerThan;
        this.percentage = percentage;
    }

    public DiscountRule toModel() {
        return new AmountBasedPercentageDiscountRule(getPriority(), greaterThanEqual, lowerThan, percentage);
    }
}
