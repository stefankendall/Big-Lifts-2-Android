package com.stefankendall.BigLifts.allprograms.formulas;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;

import java.math.BigDecimal;

public class OneRepEstimatorTests extends BLTestCase {
    public void testEstimatesOneRepMax() {
        assertEquals(OneRepEstimator.estimate(new BigDecimal("200"), 6), new BigDecimal("240.0"));
        assertEquals(OneRepEstimator.estimate(new BigDecimal("200"), 1), new BigDecimal("200.0"));
        assertEquals(OneRepEstimator.estimate(new BigDecimal("100"), 5), new BigDecimal("116.7"));
    }

    public void testHandlesNullWeight() {
        assertEquals(OneRepEstimator.estimate(null, 5), BigDecimal.ZERO);
        assertEquals(OneRepEstimator.estimate(null, 1), BigDecimal.ZERO);
    }

    public void testCutsNumbersToOneDecimalPlace() {
        assertEquals(OneRepEstimator.estimate(new BigDecimal("200.6666"), 1), new BigDecimal("200.7"));
    }

    public void testUsesSavedFormulas() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setRoundingFormula(JSettings.ROUNDING_FORMULA_BRZYCKI);
        assertEquals(OneRepEstimator.estimate(new BigDecimal("100"), 5), new BigDecimal("112.5"));
    }

    public void testHandlesNullWeightWithBrzyki() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setRoundingFormula(JSettings.ROUNDING_FORMULA_BRZYCKI);
        assertEquals(OneRepEstimator.estimate(null, 5), BigDecimal.ZERO);
    }
}
