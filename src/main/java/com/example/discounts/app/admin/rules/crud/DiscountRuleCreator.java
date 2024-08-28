package com.example.discounts.app.admin.rules.crud;

import com.example.discounts.app.admin.rules.model.DiscountRuleCreateCommand;
import com.example.discounts.domain.rules.model.DiscountRule;
import com.example.discounts.domain.rules.ports.DiscountRulesRepository;

public class DiscountRuleCreator {

    private final DiscountRulesRepository discountRulesRepository;

    public DiscountRuleCreator(DiscountRulesRepository discountRulesRepository) {
        this.discountRulesRepository = discountRulesRepository;
    }

    public DiscountRule create(DiscountRuleCreateCommand command) {
        return discountRulesRepository.save(command);
    }

}
