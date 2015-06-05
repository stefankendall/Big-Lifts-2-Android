package com.stefankendall.BigLiftsPro.allprograms.formulas;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class WilksCoefficientCalculator {
    public static BigDecimal calculate(BigDecimal weight, BigDecimal bodyweight, boolean isMale, String units) {
        if (weight == null || bodyweight == null) {
            return BigDecimal.ZERO;
        }

        Map<String, BigDecimal> k = getK(isMale);

        BigDecimal convertedBodyweight = units.equals("kg") ? bodyweight : WeightUnits.lbsToKg(bodyweight);
        List<BigDecimal> terms = Lists.newArrayList(
                k.get("a"),
                k.get("b").multiply(convertedBodyweight),
                k.get("c").multiply(convertedBodyweight.pow(2)),
                k.get("d").multiply(convertedBodyweight.pow(3)),
                k.get("e").multiply(convertedBodyweight.pow(4)),
                k.get("f").multiply(convertedBodyweight.pow(5))
        );

        BigDecimal denominator = BigDecimal.ZERO;
        for (BigDecimal term : terms) {
            denominator = denominator.add(term);
        }

        BigDecimal coefficient = new BigDecimal("500").divide(denominator, 7, RoundingMode.HALF_UP);
        BigDecimal convertedLiftedWeight = units.equals("kg") ? weight : WeightUnits.lbsToKg(weight);
        return coefficient.multiply(convertedLiftedWeight).setScale(3, RoundingMode.HALF_UP);
    }

    private static Map<String, BigDecimal> getK(boolean isMale) {
        return isMale ?
                ImmutableMap.<String, BigDecimal>builder()
                        .put("a", new BigDecimal("-216.0475144"))
                        .put("b", new BigDecimal("16.2606339"))
                        .put("c", new BigDecimal("-0.002388645"))
                        .put("d", new BigDecimal("-0.00113732"))
                        .put("e", new BigDecimal("0.00000701863"))
                        .put("f", new BigDecimal("-0.00000001291"))
                        .build()
                :
                ImmutableMap.<String, BigDecimal>builder()
                        .put("a", new BigDecimal("594.31747775582"))
                        .put("b", new BigDecimal("-27.23842536447"))
                        .put("c", new BigDecimal("0.82112226871"))
                        .put("d", new BigDecimal("-0.00930733913"))
                        .put("e", new BigDecimal("0.00004731582"))
                        .put("f", new BigDecimal("-0.00000009054"))
                        .build();
    }
}
