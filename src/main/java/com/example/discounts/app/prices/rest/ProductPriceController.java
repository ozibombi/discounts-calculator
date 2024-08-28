package com.example.discounts.app.prices.rest;

import com.example.discounts.app.prices.calculation.PriceDiscountCalculator;
import com.example.discounts.app.prices.model.ProductDiscountedPriceDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController()
@RequestMapping("api/products/{id}/prices")
@Validated
public class ProductPriceController {

    private final PriceDiscountCalculator priceCalculator;

    public ProductPriceController(PriceDiscountCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @GetMapping()
    public ProductDiscountedPriceDto calculatePrice(
            @PathVariable("id") UUID productId,
            @RequestParam("amount") @Min(0) int amount
    ) {
        return priceCalculator.calculate(productId, amount);
    }

}