package com.example.discounts.app.prices;

import com.example.discounts.app.prices.calculation.PriceDiscountCalculator;
import com.example.discounts.domain.rules.calculation.DiscountApplier;
import com.example.discounts.domain.rules.ports.DiscountRulesRepository;
import com.example.discounts.infrastructure.mongo.products.ProductsDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PricesConfiguration {

    @Bean
    public PriceDiscountCalculator priceCalculator(DiscountApplier discountApplier, ProductsDatabase productsDatabase) {
        return new PriceDiscountCalculator(discountApplier, productsDatabase);
    }

    @Bean
    public DiscountApplier discountApplier(DiscountRulesRepository discountRulesRepository) {
        return new DiscountApplier(discountRulesRepository);
    }
}
