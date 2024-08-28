package com.example.discounts.app.admin.rules.rest;

import com.example.discounts.app.admin.rules.crud.DiscountRuleCreator;
import com.example.discounts.app.admin.rules.mapper.DiscountRulesToDtoMapper;
import com.example.discounts.app.admin.rules.rest.dto.CreateDiscountRuleDto;
import com.example.discounts.domain.rules.model.DiscountRule;
import com.example.discounts.infrastructure.shared.model.DiscountRuleDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/admin/rules")
public class AdminRulesController {

    private final DiscountRuleCreator discountRuleCreator;
    private final DiscountRulesToDtoMapper mapper;

    public AdminRulesController(DiscountRuleCreator discountRuleCreator, DiscountRulesToDtoMapper mapper) {
        this.discountRuleCreator = discountRuleCreator;
        this.mapper = mapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public DiscountRuleDto addRule(@RequestBody CreateDiscountRuleDto createDiscountRuleDto) {
        return mapper.toDto(
                discountRuleCreator.create(createDiscountRuleDto.toCommand("ADMIN"))
        );
    }
}


