package com.stefankendall.BigLifts.allprograms.formulas.estimators;

import java.math.BigDecimal;

public class BrzyckiEstimator implements MaxEstimator {
    @Override
    public BigDecimal estimate(BigDecimal weight, int reps) {
        if (reps >= 37) {
            return BigDecimal.ZERO;
        }

        BigDecimal numerator = weight.multiply(new BigDecimal("36"));
        BigDecimal denominator = new BigDecimal(37 - reps);
        return numerator.divide(denominator);
    }
}
