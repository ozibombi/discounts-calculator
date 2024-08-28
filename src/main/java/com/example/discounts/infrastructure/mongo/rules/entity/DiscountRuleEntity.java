package com.example.discounts.infrastructure.mongo.rules.entity;

import com.example.discounts.infrastructure.shared.model.DiscountRuleDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "discountRules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRuleEntity {

    @Id
    private UUID id;
    private String createdBy;
    private Instant createdAt;
    private String lastUpdatedBy;
    private Instant lastUpdatedAt;
    private DiscountRuleDto rule;
}
