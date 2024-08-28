package com.example.discounts.infrastructure.mongo.products;

import com.example.discounts.app.products.model.Product;
import com.example.discounts.infrastructure.mongo.products.entity.ProductEntity;

import java.util.Optional;
import java.util.UUID;

public class ProductsDatabase {

    private final ProductsMongoDbRepository productsMongoDbRepository;

    public ProductsDatabase(ProductsMongoDbRepository productsMongoDbRepository) {
        this.productsMongoDbRepository = productsMongoDbRepository;
    }

    public Optional<Product> findById(UUID id) {
        return productsMongoDbRepository.findById(id).map(ProductEntity::toModel);
    }
}
