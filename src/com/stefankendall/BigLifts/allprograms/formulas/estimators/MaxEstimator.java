package com.stefankendall.BigLifts.allprograms.formulas.estimators;

import java.math.BigDecimal;

public interface MaxEstimator {
    public BigDecimal estimate(BigDecimal weight, int reps);
}
