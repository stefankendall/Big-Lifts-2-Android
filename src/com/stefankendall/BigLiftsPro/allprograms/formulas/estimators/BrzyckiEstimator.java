package com.stefankendall.BigLiftsPro.allprograms.formulas.estimators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BrzyckiEstimator implements MaxEstimator {
    @Override
    public BigDecimal estimate(BigDecimal weight, int reps) {
        if (reps >= 37) {
            return BigDecimal.ZERO;
        }

        BigDecimal numerator = weight.multiply(new BigDecimal("36"));
        BigDecimal denominator = new BigDecimal(37 - reps);
        return numerator.divide(denominator, 6, RoundingMode.HALF_UP);
    }
}
