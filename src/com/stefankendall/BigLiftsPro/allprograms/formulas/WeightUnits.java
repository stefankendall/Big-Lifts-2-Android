package com.stefankendall.BigLiftsPro.allprograms.formulas;

import java.math.BigDecimal;

public class WeightUnits {
    public static BigDecimal lbsToKg(BigDecimal lbs) {
        if (lbs == null) {
            return BigDecimal.ZERO;
        }

        return lbs.multiply(new BigDecimal("0.453592"));
    }
}
