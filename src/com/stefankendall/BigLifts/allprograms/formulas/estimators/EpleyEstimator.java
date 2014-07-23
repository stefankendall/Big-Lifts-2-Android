package com.stefankendall.BigLifts.allprograms.formulas.estimators;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EpleyEstimator implements MaxEstimator {
    @Override
    public BigDecimal estimate(BigDecimal weight, int reps) {
        BigDecimal factor = BigDecimal.ONE.add(new BigDecimal(reps).divide(new BigDecimal("30"), 6, RoundingMode.HALF_UP));
        return weight.multiply(factor);
    }
}
