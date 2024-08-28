package com.example.discounts.app.admin.rules.config;

import com.example.discounts.app.admin.rules.crud.DiscountRuleCreator;
import com.example.discounts.app.admin.rules.mapper.DiscountRulesToDtoMapper;
import com.example.discounts.domain.rules.ports.DiscountRulesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminRulesConfiguration {

    @Bean
    public DiscountRuleCreator discountRuleCreator(DiscountRulesRepository discountRulesRepository) {
        return new DiscountRuleCreator(discountRulesRepository);
    }

    @Bean
    public DiscountRulesToDtoMapper discountRulesToDtoMapper() {
        return new DiscountRulesToDtoMapper();
    }
}
