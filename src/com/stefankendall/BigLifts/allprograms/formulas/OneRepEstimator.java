package com.stefankendall.BigLifts.allprograms.formulas;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.stefankendall.BigLifts.allprograms.formulas.estimators.BrzyckiEstimator;
import com.stefankendall.BigLifts.allprograms.formulas.estimators.EpleyEstimator;
import com.stefankendall.BigLifts.allprograms.formulas.estimators.MaxEstimator;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class OneRepEstimator {
    public static BigDecimal estimate(BigDecimal weight, int reps) {
        if (reps == 1) {
            return weight == null ? BigDecimal.ZERO : weight.setScale(1, RoundingMode.HALF_UP);
        } else if (reps == 0 || weight == null) {
            return BigDecimal.ZERO;
        }

        Map<String, MaxEstimator> estimators = ImmutableMap.<String, MaxEstimator>builder()
                .put(JSettings.ROUNDING_FORMULA_EPLEY, new EpleyEstimator())
                .put(JSettings.ROUNDING_FORMULA_BRZYCKI, new BrzyckiEstimator())
                .build();
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        BigDecimal estimate = estimators.get(settings.roundingFormula).estimate(weight, reps);
        return estimate.setScale(1, RoundingMode.HALF_UP);
    }
}
