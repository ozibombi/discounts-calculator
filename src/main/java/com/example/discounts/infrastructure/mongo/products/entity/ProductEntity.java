package com.example.discounts.infrastructure.mongo.products.entity;

import com.example.discounts.app.products.model.Product;
import com.example.discounts.app.products.rest.dto.ProductDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document(collection = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    private UUID id;
    private String name;
    private BigDecimal unitPrice;

    public ProductDto toDto() {
        return new ProductDto(id.toString(), name, unitPrice);
    }

    public Product toModel() {
        return new Product(id, name, unitPrice);
    }
}
