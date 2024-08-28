package com.example.discounts.app.products.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID id, String name, BigDecimal unitPrice) implements Serializable {}

