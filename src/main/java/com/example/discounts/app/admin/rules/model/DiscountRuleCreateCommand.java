package com.example.discounts.app.admin.rules.model;

import com.example.discounts.infrastructure.shared.model.DiscountRuleDto;

public record DiscountRuleCreateCommand(DiscountRuleDto discountRule, String userId) {
}
