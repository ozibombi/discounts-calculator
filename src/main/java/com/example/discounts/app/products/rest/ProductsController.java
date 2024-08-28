package com.example.discounts.app.products.rest;

import com.example.discounts.app.products.rest.dto.ProductDto;
import com.example.discounts.app.products.rest.dto.ProductsResponseDto;
import com.example.discounts.infrastructure.mongo.products.ProductsMongoDbRepository;
import com.example.discounts.infrastructure.mongo.products.entity.ProductEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("api/products")
public class ProductsController {

    private final ProductsMongoDbRepository productsMongoDbRepository;

    public ProductsController(ProductsMongoDbRepository productsMongoDbRepository) {
        this.productsMongoDbRepository = productsMongoDbRepository;
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto product) {
        var entity = new ProductEntity(UUID.randomUUID(), product.getName(), product.getPrice());
        return productsMongoDbRepository.save(entity).toDto();
    }

    @GetMapping
    public ProductsResponseDto getProducts() {
        var products = productsMongoDbRepository.findAll().stream().map(ProductEntity::toDto).toList();
        return ProductsResponseDto.builder().products(products).build();
    }
}
