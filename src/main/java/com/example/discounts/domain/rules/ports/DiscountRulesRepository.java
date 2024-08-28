package com.example.discounts.domain.rules.ports;

import com.example.discounts.app.admin.rules.model.DiscountRuleCreateCommand;
import com.example.discounts.domain.rules.model.DiscountRule;

import java.util.List;

public interface DiscountRulesRepository {
    List<DiscountRule> getAll();
    DiscountRule save(DiscountRuleCreateCommand command);
}
