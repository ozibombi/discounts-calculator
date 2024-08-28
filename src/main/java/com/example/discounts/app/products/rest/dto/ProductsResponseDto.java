package com.example.discounts.app.products.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class ProductsResponseDto implements Serializable {
    List<ProductDto> products;
}