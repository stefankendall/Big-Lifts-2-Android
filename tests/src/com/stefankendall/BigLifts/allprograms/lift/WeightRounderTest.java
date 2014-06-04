package com.stefankendall.BigLifts.allprograms.lift;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
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

    public void testRoundsTo5WithDirection() {
        ((JSettings) JSettingsStore.instance().first()).roundingType = JSettings.ROUNDING_TYPE_UP;
        Assert.assertEquals(WeightRounder.round(new BigDecimal("160.1")), new BigDecimal("165"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("165")), new BigDecimal("165"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("165.1")), new BigDecimal("170"));

        ((JSettings) JSettingsStore.instance().first()).roundingType = JSettings.ROUNDING_TYPE_DOWN;
        Assert.assertEquals(WeightRounder.round(new BigDecimal("164.9")), new BigDecimal("160"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("165")), new BigDecimal("165"));
        Assert.assertEquals(WeightRounder.round(new BigDecimal("169.9")), new BigDecimal("165"));
    }

    public void testRoundsTo2p5() {
        ((JSettings) JSettingsStore.instance().first()).setRoundTo(new BigDecimal("2.5"));

        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("81.24")).compareTo(new BigDecimal("80")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("82.5")).compareTo(new BigDecimal("82.5")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("83.75")).compareTo(new BigDecimal("85")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("85")).compareTo(new BigDecimal("85")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("86.25")).compareTo(new BigDecimal("87.5")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("87.5")).compareTo(new BigDecimal("87.5")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("87.6")).compareTo(new BigDecimal("87.5")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("88.75")).compareTo(new BigDecimal("90")));
        Assert.assertEquals(0, WeightRounder.round(new BigDecimal("90")).compareTo(new BigDecimal("90")));
    }
}
