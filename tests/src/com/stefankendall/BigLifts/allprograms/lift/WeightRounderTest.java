package com.stefankendall.BigLifts.allprograms.lift;

import com.stefankendall.BigLifts.BLTestCase;
import junit.framework.Assert;

import java.math.BigDecimal;

public class WeightRounderTest extends BLTestCase {
    public void testRoundsTo5ByDefault() {
        Assert.assertEquals(WeightRounder.round(new BigDecimal("165")), new BigDecimal("165"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("166")), new BigDecimal("165"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("168")), new BigDecimal("170"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("167.5")), new BigDecimal("170"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("167.3")), new BigDecimal("165"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("85.4")), new BigDecimal("85"));
    }
}
