package com.example.discounts.domain.rules.model;


import lombok.Getter;

@Getter
public abstract sealed class AmountBasedDiscountRule extends DiscountRule permits AmountBasedAmountDiscountRule, AmountBasedPercentageDiscountRule {

    private final Integer greaterThanEqual;
    private final Integer lowerThan;

    public AmountBasedDiscountRule(int priority, Integer greaterThanEqual, Integer lowerThan) {
        super(priority);
        if (greaterThanEqual == null && lowerThan == null) {
            throw new IllegalArgumentException("Both greaterThanEqual and lowerThan are null");
        }
        if(greaterThanEqual != null && lowerThan != null && greaterThanEqual > lowerThan) {
            throw new IllegalArgumentException("Lower limit is greater than upper limit");
        }
        this.greaterThanEqual = greaterThanEqual;
        this.lowerThan = lowerThan;
    }

    protected boolean isApplicableInTermsOfQuantity(int quantity) {
        boolean isGreaterOrEqualValid = (greaterThanEqual == null) || (quantity >= greaterThanEqual);
        boolean isLowerThanValid = (lowerThan == null) || (quantity < lowerThan);

        return isGreaterOrEqualValid && isLowerThanValid;
    }
}
