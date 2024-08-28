package com.example.discounts.infrastructure.mongo.rules;


import com.example.discounts.app.admin.rules.model.DiscountRuleCreateCommand;
import com.example.discounts.domain.rules.model.DiscountRule;
import com.example.discounts.domain.rules.ports.DiscountRulesRepository;
import com.example.discounts.infrastructure.mongo.rules.entity.DiscountRuleEntity;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class DiscountRulesMongoDbRepository implements DiscountRulesRepository {

    private final MongoTemplate mongoTemplate;

    public DiscountRulesMongoDbRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<DiscountRule> getAll() {
        var entities = mongoTemplate.findAll(DiscountRuleEntity.class);
        return entities.stream()
                .map(entity -> entity.getRule().toModel()) //demeter law. Extract it
                .toList();
    }

    public DiscountRule save(DiscountRuleCreateCommand command) {
        var now = Instant.now();
        var entity = new DiscountRuleEntity(
                UUID.randomUUID(),
                command.userId(),
                now,
                null,
                null,
                command.discountRule()
        );
        return mongoTemplate.save(entity).getRule().toModel();
    }
}