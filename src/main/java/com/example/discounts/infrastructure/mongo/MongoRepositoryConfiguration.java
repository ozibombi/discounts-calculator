package com.example.discounts.infrastructure.mongo;

import com.example.discounts.infrastructure.mongo.products.ProductsDatabase;
import com.example.discounts.infrastructure.mongo.products.ProductsMongoDbRepository;
import com.example.discounts.infrastructure.mongo.rules.DiscountRulesMongoDbRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoRepositoryConfiguration {

    @Bean
    public DiscountRulesMongoDbRepository discountRulesMongoDbRepository(MongoTemplate mongoTemplate) {
        return new DiscountRulesMongoDbRepository(mongoTemplate);
    }

    @Bean
    public ProductsDatabase productsDatabase(ProductsMongoDbRepository productsMongoDbRepository) {
        return new ProductsDatabase(productsMongoDbRepository);
    }
}
