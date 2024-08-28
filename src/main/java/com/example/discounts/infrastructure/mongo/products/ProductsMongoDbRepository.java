package com.example.discounts.infrastructure.mongo.products;

import com.example.discounts.infrastructure.mongo.products.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductsMongoDbRepository extends MongoRepository<ProductEntity, UUID> {
}
