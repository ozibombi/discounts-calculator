package com.example.discounts.infrastructure.shared.model;

import com.example.discounts.domain.rules.model.AmountBasedAmountDiscountRule;
import com.example.discounts.domain.rules.model.DiscountRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public non-sealed class AmountBasedAmountDiscountRuleDto extends DiscountRuleDto {

    private final Integer greaterThanEqual;
    private final Integer lowerThan;
    private final BigDecimal discount;

    @JsonCreator
    public AmountBasedAmountDiscountRuleDto(
            @JsonProperty("priority") int priority,
            @JsonProperty("greaterThanEqual") Integer greaterThanEqual,
            @JsonProperty("lowerThan")Integer lowerThan,
            @JsonProperty("discount") BigDecimal discount
    ) {
        super(priority);
        this.greaterThanEqual = greaterThanEqual;
        this.lowerThan = lowerThan;
        this.discount = discount;
    }

    public DiscountRule toModel() {
        return new AmountBasedAmountDiscountRule(getPriority(), greaterThanEqual, lowerThan, discount);
    }
}
