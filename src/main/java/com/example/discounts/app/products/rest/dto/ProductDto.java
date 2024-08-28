package com.example.discounts.app.products.rest.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto implements Serializable {
    private String id;
    private String name;
    private BigDecimal price;
}
