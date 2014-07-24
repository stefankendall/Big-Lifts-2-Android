package com.stefankendall.BigLifts.allprograms.formulas;

import com.stefankendall.BigLifts.BLTestCase;
import junit.framework.Assert;

import java.math.BigDecimal;

public class WeightUnitsTests extends BLTestCase {
    public void testConvertsLbsToKg() {
        Assert.assertTrue(WeightUnits.lbsToKg(null).compareTo(BigDecimal.ZERO) == 0);
        Assert.assertTrue(WeightUnits.lbsToKg(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0);
        Assert.assertTrue(WeightUnits.lbsToKg(new BigDecimal("10")).compareTo(new BigDecimal("4.53592")) == 0);
    }
}
