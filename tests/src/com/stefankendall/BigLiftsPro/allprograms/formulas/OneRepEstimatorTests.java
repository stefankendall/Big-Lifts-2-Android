package com.stefankendall.BigLiftsPro.allprograms.formulas;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class OneRepEstimatorTests extends BLTestCase {
    public void testEstimatesOneRepMax() {
        Assert.assertEquals(OneRepEstimator.estimate(new BigDecimal("200"), 6), new BigDecimal("240.0"));
        Assert.assertEquals(OneRepEstimator.estimate(new BigDecimal("200"), 1), new BigDecimal("200.0"));
        Assert.assertEquals(OneRepEstimator.estimate(new BigDecimal("100"), 5), new BigDecimal("116.7"));
    }

    public void testHandlesNullWeight() {
        Assert.assertEquals(OneRepEstimator.estimate(null, 5), BigDecimal.ZERO);
        Assert.assertEquals(OneRepEstimator.estimate(null, 1), BigDecimal.ZERO);
    }

    public void testCutsNumbersToOneDecimalPlace() {
        Assert.assertEquals(OneRepEstimator.estimate(new BigDecimal("200.6666"), 1), new BigDecimal("200.7"));
    }

    public void testUsesSavedFormulas() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setRoundingFormula(JSettings.ROUNDING_FORMULA_BRZYCKI);
        Assert.assertEquals(OneRepEstimator.estimate(new BigDecimal("100"), 5), new BigDecimal("112.5"));
    }

    public void testHandlesNullWeightWithBrzyki() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setRoundingFormula(JSettings.ROUNDING_FORMULA_BRZYCKI);
        Assert.assertEquals(OneRepEstimator.estimate(null, 5), BigDecimal.ZERO);
    }
}
