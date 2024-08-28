package com.example.discounts.app.admin.rules.rest.dto;

import com.example.discounts.app.admin.rules.model.DiscountRuleCreateCommand;
import com.example.discounts.infrastructure.shared.model.DiscountRuleDto;
import lombok.Setter;

@Setter
public class CreateDiscountRuleDto {
    private DiscountRuleDto discountRule;

    public DiscountRuleCreateCommand toCommand(String userId) {
        return new DiscountRuleCreateCommand(discountRule, userId);
    }
}
